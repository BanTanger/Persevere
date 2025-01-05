package com.bantanger.elegant.order;

import com.bantanger.commons.annotation.FieldDesc;
import com.bantanger.elegant.pipeline.BizEnum;
import lombok.Data;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/5
 */
@Data
public class OrderRequest {
    @FieldDesc(name = "业务编码")
    private BizLineEnum bizCode;
    @FieldDesc(name = "用户ID")
    private Long userId;
    @FieldDesc(name = "商品ID")
    private String productId;
    @FieldDesc(name = "商家ID")
    private String businessId;
}
