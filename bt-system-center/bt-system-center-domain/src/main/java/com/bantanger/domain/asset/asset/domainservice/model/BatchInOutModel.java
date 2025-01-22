package com.bantanger.domain.asset.asset.domainservice.model;

import com.bantanger.common.annotation.FieldDesc;
import com.bantanger.domain.asset.record.enums.InOutBizType;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 * @author chensongmin
 * @description 批量出入库模型
 * @date 2025/1/15
 */
@Setter
@Getter
public class BatchInOutModel {

    @FieldDesc(name = "资产名称")
    private String name;

    @FieldDesc(name = "出入库业务类型")
    private InOutBizType inOutBizType;

    @FieldDesc(name = "资产仓库编码ID")
    private Long wareHouseId;

    @FieldDesc(name = "唯一编码簇")
    private List<String> uniqueCodes;

    @FieldDesc(name = "批次号")
    private String batchNo;

    @FieldDesc(name = "资产skuId")
    private Long skuId;

    @FieldDesc(name = "操作用户")
    private String operateUser;

}
