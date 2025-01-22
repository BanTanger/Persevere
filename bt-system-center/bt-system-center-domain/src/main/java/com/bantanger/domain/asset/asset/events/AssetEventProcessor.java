package com.bantanger.domain.asset.asset.events;

import com.bantanger.domain.asset.asset.Asset;
import com.bantanger.domain.asset.asset.domainservice.model.AssetBizInfo;
import com.bantanger.domain.asset.lifecycle.AssetLifecycle;
import com.bantanger.domain.asset.lifecycle.creator.AssetLifecycleCreator;
import com.bantanger.domain.asset.lifecycle.service.IAssetLifecycleService;
import com.bantanger.domain.asset.record.creator.AssetInOutRecordCreator;
import com.bantanger.domain.asset.record.enums.InOutType;
import com.bantanger.domain.asset.record.service.IAssetInOutRecordService;
import com.bantanger.domain.asset.warehouse.service.IWarehouseService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author chensongmin
 * @description 资产事件监听器
 * @date 2025/1/15
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class AssetEventProcessor {

    private final IWarehouseService warehouseService;
    private final IAssetLifecycleService assetLifecycleService;
    private final IAssetInOutRecordService assetInOutRecordService;

    /**
     * [资产生命周期子领域] 处理资产入库事件
     */
    @EventListener
    public void handleAssetInForLifecycle(AssetEvents.AssetInEvent assetInEvent) {
        AssetBizInfo assetBizInfo = assetInEvent.bizInfo();
        Asset asset = assetInEvent.asset();
        // 艺术性的处理采用批号的方式进行批量插入操作，避免每次事件都插入一条记录，这样有性能问题
        List<AssetLifecycleCreator> assetLifecycleCreators = assetBizInfo.getUniqueCodes().stream()
            .map(code -> {
                AssetLifecycleCreator creator = new AssetLifecycleCreator();
                creator.setName(asset.getName());
                creator.setAssetsId(asset.getId());
                creator.setUniqueCode(code);
                creator.setRemark(InOutType.IN.getName());
                creator.setHouseId(asset.getWarehouseId());
                // 不用关注findById的性能，那是仓库服务应该考虑的事，最少知道原则，那个优化可以加缓存
                creator.setHouseName(warehouseService.findById(asset.getWarehouseId()).getName());
                creator.setInOutBizType(assetBizInfo.getInOutBizType());
                creator.setInOutType(InOutType.IN);
                creator.setOperateUser(assetBizInfo.getOperateUser());
                creator.setGenBatchNo(assetBizInfo.getGenBatchNo());
                creator.setBatchNo(assetBizInfo.getBatchNo());
                return creator;
            }).collect(Collectors.toList());
        assetLifecycleService.batchCreateLifecycle(assetBizInfo.getGenBatchNo(), assetLifecycleCreators);
    }

    /**
     * [资产生命周期子领域] 处理资产出库事件
     */
    @EventListener
    public void handleAssetOutForLifecycle(AssetEvents.AssetOutEvent assetOutEvent) {
        AssetBizInfo assetBizInfo = assetOutEvent.bizInfo();
        Asset asset = assetOutEvent.asset();
        // 艺术性的处理采用批号的方式进行批量插入操作，避免每次事件都插入一条记录，这样有性能问题
        List<AssetLifecycleCreator> assetLifecycleCreators = assetBizInfo.getUniqueCodes().stream()
            .map(code -> {
                AssetLifecycleCreator creator = new AssetLifecycleCreator();
                creator.setName(asset.getName());
                creator.setAssetsId(asset.getId());
                creator.setUniqueCode(code);
                creator.setRemark(InOutType.OUT.getName());
                creator.setHouseId(asset.getWarehouseId());
                // 不用关注findById的性能，那是仓库服务应该考虑的事，最少知道原则，那个优化可以加缓存
                creator.setHouseName(warehouseService.findById(asset.getWarehouseId()).getName());
                creator.setInOutBizType(assetBizInfo.getInOutBizType());
                creator.setInOutType(InOutType.OUT);
                creator.setOperateUser(assetBizInfo.getOperateUser());
                creator.setGenBatchNo(assetBizInfo.getGenBatchNo());
                creator.setBatchNo(assetBizInfo.getBatchNo());
                return creator;
            }).collect(Collectors.toList());
        assetLifecycleService.batchCreateLifecycle(assetBizInfo.getGenBatchNo(), assetLifecycleCreators);
    }

    /**
     * 保存出库记录
     *
     * @param assetOutEvent
     */
    @EventListener
    public void handleAssetOutForRecord(AssetEvents.AssetOutEvent assetOutEvent) {
        AssetBizInfo bizInfo = assetOutEvent.bizInfo();
        AssetInOutRecordCreator creator = new AssetInOutRecordCreator();
        creator.setInOutBizType(bizInfo.getInOutBizType());
        creator.setInOutType(InOutType.OUT);
        creator.setBatchNo(bizInfo.getBatchNo());
        creator.setGenBatchNo(bizInfo.getGenBatchNo());
        creator.setOperateUser(bizInfo.getOperateUser());
        creator.setTotalCount(bizInfo.getUniqueCodes().size());
        assetInOutRecordService.createAssetInOutRecord(bizInfo.getUniqueCodes(), creator);
    }

    /**
     * 保存入库记录
     * @param inEvent
     */
    @EventListener
    public void handleAssetInForRecord(AssetEvents.AssetInEvent inEvent) {
        AssetBizInfo bizInfo = inEvent.bizInfo();
        AssetInOutRecordCreator creator = new AssetInOutRecordCreator();
        creator.setInOutBizType(bizInfo.getInOutBizType());
        creator.setInOutType(InOutType.IN);
        creator.setBatchNo(bizInfo.getBatchNo());
        creator.setGenBatchNo(bizInfo.getGenBatchNo());
        creator.setOperateUser(bizInfo.getOperateUser());
        creator.setTotalCount(bizInfo.getUniqueCodes().size());
        assetInOutRecordService.createAssetInOutRecord(bizInfo.getUniqueCodes(), creator);
    }

}
