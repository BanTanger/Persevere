package com.bantanger.domain.trade.order.domainservice.model;

import com.bantanger.common.annotation.FieldDesc;
import java.math.BigDecimal;
import lombok.Data;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/8
 */
@Data
public class OrderItemModel {

    @FieldDesc(name = "真实金额")
    private BigDecimal realAmount;
    @FieldDesc(name = "计量数量")
    private Integer itemCount;
    @FieldDesc(name = "商品 sku 编号")
    private Long skuId;
    @FieldDesc(name = "商品名称")
    private String goodsName;
    @FieldDesc(name = "费用描述")
    private String feeRemark;
}
