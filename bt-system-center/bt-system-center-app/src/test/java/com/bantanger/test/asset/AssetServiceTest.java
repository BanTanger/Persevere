package com.bantanger.test.asset;

import com.bantanger.domain.asset.asset.domainservice.IAssetDomainService;
import com.bantanger.domain.asset.warehouse.creator.WarehouseCreator;
import com.bantanger.domain.asset.warehouse.service.IWarehouseService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
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

}
