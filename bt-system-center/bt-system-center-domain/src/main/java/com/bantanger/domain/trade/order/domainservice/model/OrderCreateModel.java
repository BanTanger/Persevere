package com.bantanger.domain.trade.order.domainservice.model;

import com.bantanger.common.annotation.FieldDesc;
import com.bantanger.domain.CodeValue;
import com.bantanger.domain.pay.PayItem;
import com.bantanger.domain.trade.order.model.enums.OrderType;
import com.bantanger.domain.user.AccountType;
import java.util.List;
import lombok.Data;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/8
 */
@Data
public class OrderCreateModel {

    @FieldDesc(name = "账号ID")
    private Long accountId;
    @FieldDesc(name = "账号类型: 1-个人, 2-企业")
    private AccountType accountType;
    @FieldDesc(name = "订单类型: 1-普通订单, 2-秒杀订单")
    private OrderType orderType;
    @FieldDesc(name = "支付详情(虚拟资产抵扣项)")
    private List<PayItem> payItemList;
    @FieldDesc(name = "订单附加信息")
    private List<CodeValue> attrs;
    @FieldDesc(name = "订单项列表")
    private List<OrderItemModel> itemInfoList;
    @FieldDesc(name = "操作用户")
    private String operateUser;
}
