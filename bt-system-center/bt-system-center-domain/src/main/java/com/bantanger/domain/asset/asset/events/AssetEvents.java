package com.bantanger.domain.asset.asset.events;

import com.bantanger.domain.asset.asset.Asset;
import com.bantanger.domain.asset.asset.domainservice.model.AssetBizInfo;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/15
 */
public interface AssetEvents {

    /**
     * 资产入库事件
     * @param asset
     * @param bizInfo
     */
    record AssetInEvent(Asset asset, AssetBizInfo bizInfo) {}

    /**
     * 资产出库事件
     * @param asset
     * @param bizInfo
     */
    record AssetOutEvent(Asset asset, AssetBizInfo bizInfo) {}

}
