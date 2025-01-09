package com.bantanger.domain.trade.order.domainservice.model;

import com.bantanger.common.annotation.FieldDesc;
import com.bantanger.order.common.pay.PayItem;
import java.time.Instant;
import java.util.List;
import lombok.Data;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/8
 */
@Data
public class OrderCompleteModel {

    @FieldDesc(name = "唯一流水号")
    private Long flowNo;
    @FieldDesc(name = "支付详情")
    private List<PayItem> payItemList;
    @FieldDesc(name = "支付时间")
    private Instant payTime;
}
