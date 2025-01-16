package com.bantanger.domain.asset.asset.domainservice;

import com.bantanger.domain.asset.asset.domainservice.model.BatchInOutModel;
import com.bantanger.domain.asset.asset.domainservice.model.TransferModel;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/15
 */
public interface IAssetDomainService {

    /**
     * 资产入库
     * @param batchInOutModel
     */
    void handleAssetIn(BatchInOutModel batchInOutModel);

    /**
     * 资产出库
     * @param batchInOutModel
     */
    void handleAssetOut(BatchInOutModel batchInOutModel);

    /**
     * 资产调拨，转移
     * @param transferModel
     */
    void handleAssetTransfer(TransferModel transferModel);

}
