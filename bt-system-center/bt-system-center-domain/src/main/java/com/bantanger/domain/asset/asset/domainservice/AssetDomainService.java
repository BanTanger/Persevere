package com.bantanger.domain.asset.asset.domainservice;

import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.IdUtil;
import com.bantanger.common.constants.CodeEnum;
import com.bantanger.common.enumtype.AssetErrorType;
import com.bantanger.common.exception.BusinessException;
import com.bantanger.domain.asset.asset.Asset;
import com.bantanger.domain.asset.asset.QAsset;
import com.bantanger.domain.asset.asset.creator.AssetCreator;
import com.bantanger.domain.asset.asset.domainservice.model.AssetBizInfo;
import com.bantanger.domain.asset.asset.domainservice.model.BatchInOutModel;
import com.bantanger.domain.asset.asset.domainservice.model.TransferModel;
import com.bantanger.domain.asset.asset.mapper.AssetMapper;
import com.bantanger.domain.asset.asset.repository.AssetRepository;
import com.bantanger.domain.asset.record.enums.InOutBizType;
import com.bantanger.jpa.support.EntityOperations;
import com.querydsl.core.BooleanBuilder;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/22
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AssetDomainService implements IAssetDomainService {

    private final AssetRepository assetRepository;

    @Override
    @Transactional
    public void handleAssetIn(BatchInOutModel batchInOutModel) {
        Assert.notEmpty(batchInOutModel.getUniqueCodes());
        AssetBizInfo bizInfo = AssetBizInfo.builder()
            .inOutBizType(batchInOutModel.getInOutBizType())
            .uniqueCodes(batchInOutModel.getUniqueCodes())
            .batchNo(batchInOutModel.getBatchNo())
            .genBatchNo(IdUtil.simpleUUID())
            .operateUser(batchInOutModel.getOperateUser())
            .build();

        batchInOutModel.getUniqueCodes().forEach(code -> {
            Optional<Asset> assetInfo = assetRepository.findOne(new BooleanBuilder()
                .and(QAsset.asset.uniqueCode.eq(code))
                .and(QAsset.asset.warehouseId.eq(batchInOutModel.getWareHouseId()))
                .and(QAsset.asset.skuId.eq(batchInOutModel.getSkuId())));

            assetInfo.ifPresentOrElse(o -> {
                // 如果存在，则更新入库
                EntityOperations.doUpdate(assetRepository)
                    .load(() -> o)
                    .update(asset -> asset.in(bizInfo))
                    .execute();
            }, () -> {
                // 如果不存在，则新增入库
                AssetCreator assetCreator = new AssetCreator();
                assetCreator.setWarehouseId(batchInOutModel.getWareHouseId());
                assetCreator.setName(batchInOutModel.getName());
                assetCreator.setSkuId(batchInOutModel.getSkuId());
                assetCreator.setUniqueCode(code);
                EntityOperations.doCreate(assetRepository)
                    .create(() -> AssetMapper.INSTANCE.dtoToEntity(assetCreator))
                    .update(asset -> asset.in(bizInfo))
                    .execute();
            });
        });
    }

    @Override
    @Transactional
    public void handleAssetOut(BatchInOutModel batchInOutModel) {
        Assert.notEmpty(batchInOutModel.getUniqueCodes());
        AssetBizInfo bizInfo = AssetBizInfo.builder()
            .inOutBizType(batchInOutModel.getInOutBizType())
            .uniqueCodes(batchInOutModel.getUniqueCodes())
            .batchNo(batchInOutModel.getBatchNo())
            .genBatchNo(IdUtil.simpleUUID())
            .operateUser(batchInOutModel.getOperateUser())
            .build();

        batchInOutModel.getUniqueCodes().forEach(code -> {
            Optional<Asset> assetInfo = assetRepository.findOne(new BooleanBuilder()
                .and(QAsset.asset.uniqueCode.eq(code))
                .and(QAsset.asset.warehouseId.eq(batchInOutModel.getWareHouseId()))
                .and(QAsset.asset.skuId.eq(batchInOutModel.getSkuId())));

            assetInfo.ifPresentOrElse(o -> {
                // 如果存在，则更新出库
                EntityOperations.doUpdate(assetRepository)
                    .load(() -> o)
                    .update(asset -> asset.out(bizInfo))
                    .execute();
            }, () -> {
                // 如果不存在，则抛出异常
                throw new BusinessException(AssetErrorType.ASSET_CODE_NOT_EXIST, "资产编码:" + code);
            });
        });
    }

    @Override
    @Transactional
    public void handleAssetTransfer(TransferModel transferModel) {
        Assert.notEmpty(transferModel.getUniqueCodes());
        String genBatchNo = IdUtil.simpleUUID();
        Optional<Asset> asset = assetRepository.findOne(new BooleanBuilder()
            .and(QAsset.asset.uniqueCode.eq(transferModel.getUniqueCodes().get(0))));
        // 转出
        BatchInOutModel outModel = new BatchInOutModel();
        outModel.setInOutBizType(InOutBizType.OUT_TRANSFER);
        outModel.setUniqueCodes(transferModel.getUniqueCodes());
        outModel.setBatchNo(transferModel.getBatchNo());
        outModel.setSkuId(transferModel.getSkuId());
        outModel.setOperateUser(transferModel.getOperateUser());
        this.handleAssetOut(outModel);
        log.info("处理出库完成，仓库id:{}, 批次号:{}, 自动批号:{}",
            transferModel.getTransferOutHouseId(), transferModel.getBatchNo(), genBatchNo);

        // 转入
        BatchInOutModel inModel = new BatchInOutModel();
        inModel.setName(asset.get().getName());
        inModel.setInOutBizType(InOutBizType.IN_TRANSFER);
        inModel.setUniqueCodes(transferModel.getUniqueCodes());
        inModel.setBatchNo(transferModel.getBatchNo());
        inModel.setSkuId(transferModel.getSkuId());
        inModel.setOperateUser(transferModel.getOperateUser());
        this.handleAssetIn(inModel);
        log.info("处理入库完成，仓库id:{}, 批次号:{}, 自动批号:{}",
            transferModel.getTransferOutHouseId(), transferModel.getBatchNo(), genBatchNo);
    }
}
