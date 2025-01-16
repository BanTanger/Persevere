package com.bantanger.domain.asset.asset.events;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
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

    /**
     * [资产生命周期子领域] 处理资产入库事件
     */
    public void handleAssetInForLifecycle(AssetEvents.AssetInEvent assetInEvent) {

    }

    /**
     * [资产生命周期子领域] 处理资产出库事件
     */
    public void handleAssetOutForLifecycle(AssetEvents.AssetOutEvent assetOutEvent) {

    }

}
