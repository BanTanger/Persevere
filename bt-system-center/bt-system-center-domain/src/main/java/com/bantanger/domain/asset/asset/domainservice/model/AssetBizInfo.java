package com.bantanger.domain.asset.asset.domainservice.model;

import com.bantanger.common.annotation.FieldDesc;
import com.bantanger.domain.asset.assetrecord.InOutBizType;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author chensongmin
 * @description 资产业务详细描述信息
 * @date 2025/1/15
 */
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AssetBizInfo {

    @FieldDesc(name = "出入库业务类型")
    private InOutBizType inOutBizType;

    @FieldDesc(name = "唯一编码簇")
    private List<String> uniqueCodes;

    @FieldDesc(name = "批次号")
    private String batchNo;

    @FieldDesc(name = "自动生成批次号")
    private String genBatchNo;

    @FieldDesc(name = "操作人")
    private String operateUser;

}
