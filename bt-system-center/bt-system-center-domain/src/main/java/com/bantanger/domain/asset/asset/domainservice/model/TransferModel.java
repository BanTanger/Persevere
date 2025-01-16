package com.bantanger.domain.asset.asset.domainservice.model;

import com.bantanger.common.annotation.FieldDesc;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * @author chensongmin
 * @description 资产调拨模型
 * @date 2025/1/15
 */
@Setter
@Getter
public class TransferModel {

    @FieldDesc(name = "skuId")
    private Long skuId;

    @FieldDesc(name = "转入仓库id")
    private Long transferInHouseId;

    @FieldDesc(name = "转出仓库id")
    private Long transferOutHouseId;

    @FieldDesc(name = "唯一编码簇")
    private List<String> uniqueCodes;

    @FieldDesc(name = "批次号")
    private String batchNo;

    @FieldDesc(name = "操作用户")
    private String operateUser;

}
