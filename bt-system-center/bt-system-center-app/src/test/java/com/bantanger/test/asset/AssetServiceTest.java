package com.bantanger.test.asset;

import com.bantanger.domain.asset.asset.domainservice.IAssetDomainService;
import com.bantanger.domain.asset.asset.domainservice.model.BatchInOutModel;
import com.bantanger.domain.asset.asset.domainservice.model.TransferModel;
import com.bantanger.domain.asset.record.enums.InOutBizType;
import com.bantanger.domain.asset.warehouse.creator.WarehouseCreator;
import com.bantanger.domain.asset.warehouse.service.IWarehouseService;
import jakarta.annotation.Resource;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/22
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
//@DataJpaTest
public class AssetServiceTest {

    @Resource
    private IAssetDomainService assetDomainService;
    @Resource
    private IWarehouseService warehouseService;

    @Test
    public void testWarehouseCreate() {
        WarehouseCreator creator = new WarehouseCreator();
        creator.setCreateUser("半糖");
        creator.setName("测试仓库");
        creator.setCode("warehouse-test");
        creator.setAddress("默认地址");
        warehouseService.createWarehouse(creator);
    }

    @Test
    public void testWarehouseCreate2() {
        WarehouseCreator creator = new WarehouseCreator();
        creator.setCreateUser("半糖");
        creator.setName("测试仓库-2");
        creator.setCode("warehouse-test-2");
        creator.setAddress("默认地址-2");
        warehouseService.createWarehouse(creator);
    }

    @Test
    public void testSkuIn() {
        // 模拟批量入库逻辑
        List<String> uniqueCodes = Lists.newArrayList("123456", "234567");
        BatchInOutModel model = new BatchInOutModel();
        model.setSkuId(3L);
        // 前端展示批次号
        model.setBatchNo("20250122-1");
        model.setInOutBizType(InOutBizType.IN_INITIAL);
        model.setOperateUser("半糖");
        model.setWareHouseId(1L);
        model.setUniqueCodes(uniqueCodes);
        model.setName("华为远峰蓝");
        assetDomainService.handleAssetIn(model);
    }

    @Test
    public void testSkuIn2() {
        // 模拟批量入库逻辑
        List<String> uniqueCodes = Lists.newArrayList("222222", "333333", "444444");
        BatchInOutModel model = new BatchInOutModel();
        model.setSkuId(2L);
        // 前端展示批次号
        model.setBatchNo("20250122-2");
        model.setInOutBizType(InOutBizType.IN_INITIAL);
        model.setOperateUser("半糖");
        model.setWareHouseId(1L);
        model.setUniqueCodes(uniqueCodes);
        model.setName("Macbook m3 pro 2025");
        assetDomainService.handleAssetIn(model);
    }

    @Test
    public void testSkuOut() {
        // 模拟批量出库逻辑
        List<String> uniqueCodes = Lists.newArrayList("123456");
        BatchInOutModel model = new BatchInOutModel();
        model.setSkuId(3L);
        // 前端展示批次号
        model.setBatchNo("20250122-1");
        model.setInOutBizType(InOutBizType.OUT_SALE);
        model.setOperateUser("测试用户-1");
        model.setWareHouseId(1L);
        model.setUniqueCodes(uniqueCodes);
        model.setName("华为远峰蓝");
        assetDomainService.handleAssetOut(model);
    }

    @Test
    public void testSkuTransfer() {
        // 模拟批量转移逻辑
        List<String> uniqueCodes = Lists.newArrayList("222222", "333333", "444444");
        TransferModel model = new TransferModel();
        model.setSkuId(2L);
        // 前端展示批次号
        model.setBatchNo("20250122-3");
        model.setOperateUser("半糖");
        model.setTransferOutHouseId(1L);
        model.setTransferInHouseId(2L);
        model.setUniqueCodes(uniqueCodes);
        assetDomainService.handleAssetTransfer(model);
    }

}
