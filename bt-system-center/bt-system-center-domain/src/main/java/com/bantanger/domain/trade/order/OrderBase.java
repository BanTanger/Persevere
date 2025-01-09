package com.bantanger.domain.trade.order;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.NumberUtil;
import com.bantanger.codegen.processor.api.GenCreateRequest;
import com.bantanger.codegen.processor.api.GenQueryRequest;
import com.bantanger.codegen.processor.api.GenResponse;
import com.bantanger.codegen.processor.api.GenUpdateRequest;
import com.bantanger.codegen.processor.controller.GenController;
import com.bantanger.codegen.processor.creator.GenCreator;
import com.bantanger.codegen.processor.creator.IgnoreCreator;
import com.bantanger.codegen.processor.mapper.GenMapper;
import com.bantanger.codegen.processor.query.GenQuery;
import com.bantanger.codegen.processor.query.QueryItem;
import com.bantanger.codegen.processor.repository.GenRepository;
import com.bantanger.codegen.processor.service.GenService;
import com.bantanger.codegen.processor.service.GenServiceImpl;
import com.bantanger.codegen.processor.updater.GenUpdater;
import com.bantanger.codegen.processor.updater.IgnoreUpdater;
import com.bantanger.codegen.processor.vo.GenVo;
import com.bantanger.common.annotation.FieldDesc;
import com.bantanger.common.annotation.TypeConverter;
import com.bantanger.common.constants.GenSourceConstants;
import com.bantanger.common.constants.ValidStatus;
import com.bantanger.common.enumtype.OrderErrorType;
import com.bantanger.common.enumtype.converter.CodeValueListConverter;
import com.bantanger.common.exception.BusinessException;
import com.bantanger.common.model.CodeValue;
import com.bantanger.domain.trade.order.domainservice.model.OrderCompleteModel;
import com.bantanger.domain.trade.order.domainservice.model.OrderCreateModel;
import com.bantanger.domain.trade.order.events.OrderEvents.OrderCreateEvent;
import com.bantanger.domain.trade.order.model.enums.OrderState;
import com.bantanger.domain.trade.order.model.enums.OrderStateConverter;
import com.bantanger.domain.trade.order.model.enums.OrderType;
import com.bantanger.domain.trade.order.model.enums.OrderTypeConverter;
import com.bantanger.domain.user.AccountType;
import com.bantanger.domain.user.AccountTypeConverter;
import com.bantanger.jpa.converter.ValidStatusConverter;
import com.bantanger.jpa.support.BaseJpaAggregate;
import com.bantanger.order.common.pay.PayItem;
import com.bantanger.order.common.pay.PayItemListConverter;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import lombok.Data;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/8
 */
@GenVo(pkgName = "com.bantanger.domain.trade.order.vo")
@GenCreator(pkgName = "com.bantanger.domain.trade.order.creator")
@GenUpdater(pkgName = "com.bantanger.domain.trade.order.updater")
@GenRepository(pkgName = "com.bantanger.domain.trade.order.repository")
@GenService(pkgName = "com.bantanger.domain.trade.order.service")
@GenServiceImpl(pkgName = "com.bantanger.domain.trade.order.service")
@GenQuery(pkgName = "com.bantanger.domain.trade.order.query")
@GenMapper(pkgName = "com.bantanger.domain.trade.order.mapper")
@GenController(pkgName = "com.bantanger.trigger.http.trade.order", sourcePath = GenSourceConstants.GEN_CONTROLLER_SOURCE)
@GenCreateRequest(pkgName = "com.bantanger.api.trade.order.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenUpdateRequest(pkgName = "com.bantanger.api.trade.order.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenQueryRequest(pkgName = "com.bantanger.api.trade.order.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenResponse(pkgName = "com.bantanger.api.trade.order.response", sourcePath = GenSourceConstants.GEN_API_SOURCE)
//@GenFeign(pkgName = "com.bantanger.trigger.api.service", sourcePath = GenSourceConstants.GEN_API_SOURCE, serverName = "trigger")
@Entity
@Table(name = "order_base")
@Data
public class OrderBase extends BaseJpaAggregate {

    @FieldDesc(name = "唯一流水号")
    @IgnoreUpdater
    private Long flowNo;

    @NotNull(message = "订单金额不能为空")
    @FieldDesc(name = "订单金额")
    private BigDecimal totalAmount;

    @FieldDesc(name = "账号ID")
    private Long accountId;

    @FieldDesc(name = "账号类型: 1-个人, 2-企业")
    @Convert(converter = AccountTypeConverter.class)
    @TypeConverter(toTypeFullName = "java.lang.Integer")
    private AccountType accountType;

    // 根据不同的订单类型创建不同的状态机
    @FieldDesc(name = "订单类型: 1-普通订单, 2-秒杀订单")
    @Convert(converter = OrderTypeConverter.class)
    @TypeConverter(toTypeFullName = "java.lang.Integer")
    @IgnoreUpdater
    @QueryItem
    private OrderType orderType;

    @FieldDesc(name = "支付详情(虚拟资产抵扣项)")
    @IgnoreCreator
    @IgnoreUpdater
    @Convert(converter = PayItemListConverter.class)
    @Column(columnDefinition = "json")
    private List<PayItem> payItemList;

    @FieldDesc(name = "待支付金额")
    @Min(value = 0, message = "待支付金额不能小于0")
    private BigDecimal waitPay;

    @FieldDesc(name = "支付时间")
    @IgnoreCreator
    @IgnoreUpdater
    private Instant payTime;

    @FieldDesc(name = "订单状态")
    @Convert(converter = OrderStateConverter.class)
    @TypeConverter(toTypeFullName = "java.lang.Integer")
    @IgnoreUpdater
    @IgnoreCreator
    private OrderState orderState;

    @FieldDesc(name = "订单附加信息")
    @Convert(converter = CodeValueListConverter.class)
    @Column(columnDefinition = "json")
    private List<CodeValue> attrs;

    @FieldDesc(name = "是否开票")
    @Convert(converter = ValidStatusConverter.class)
    @IgnoreUpdater
    @IgnoreCreator
    private ValidStatus invoiceFlag;

    @Convert(converter = ValidStatusConverter.class)
    @IgnoreUpdater
    @IgnoreCreator
    private ValidStatus validStatus;

    /**
     * 订单初始化
     * @param createModel
     */
    public void init(OrderCreateModel createModel) {
        setValidStatus(ValidStatus.VALID);
        setInvoiceFlag(ValidStatus.INVALID);

        BigDecimal total = getTotalAmount();
        // 有虚拟资产抵扣
        if (!CollectionUtil.isEmpty(createModel.getPayItemList())) {
            setPayItemList(createModel.getPayItemList());
            BigDecimal hasPay = createModel.getPayItemList().stream()
                .map(PayItem::getMoney)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
            if (NumberUtil.isGreater(hasPay, total)) {
                throw new BusinessException(OrderErrorType.PAY_TOO_BIG);
            } else if (NumberUtil.equals(hasPay, total)) {
                setOrderState(OrderState.PAY_SUCCESS);
                setWaitPay(BigDecimal.ZERO);
            } else {
                setOrderState(OrderState.WAIT_PAY);
                setWaitPay(NumberUtil.sub(total, hasPay));
            }
        } else { // 没有虚拟资产抵扣
            setPayItemList(Collections.EMPTY_LIST);
            // 无需支付（在交易模块可能已经减免或优惠抵扣了）
            if (NumberUtil.equals(BigDecimal.ZERO, total)) {
                setOrderState(OrderState.PAY_SUCCESS);
            } else { // 有待支付金额
                setWaitPay(total);
                setOrderState(OrderState.WAIT_PAY);
            }
        }
        // 发送领域事件
        registerEvent(new OrderCreateEvent(this, createModel));
    }

    /**
     * 订单完成
     * @param completeModel
     */
    public void complete(OrderCompleteModel completeModel) {
        if (!Objects.equals(OrderState.WAIT_PAY, getOrderState())) {
            throw new BusinessException(OrderErrorType.ORDER_NOT_WAIT_PAY);
        }
        if (CollectionUtil.isEmpty(completeModel.getPayItemList())) {
            throw new BusinessException(OrderErrorType.PAY_LIST_IS_NULL);
        }
        // 计算需支付金额
        BigDecimal hasPay = completeModel.getPayItemList().stream()
            .map(PayItem::getMoney)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        if (!NumberUtil.equals(hasPay, getWaitPay())) {
            throw new BusinessException(OrderErrorType.PAY_AMOUNT_NOT_EQUAL_WAIT_PAY);
        }
        setPayTime(completeModel.getPayTime());
        List<PayItem> payItemList = getPayItemList();
        payItemList.addAll(completeModel.getPayItemList());
        setPayItemList(payItemList);
        setOrderState(OrderState.PAY_SUCCESS);
    }

    /**
     * 取消订单
     */
    public void cancel() {
        // 校验订单状态, 只有待支付才能取消订单
        if (!Objects.equals(OrderState.WAIT_PAY, getOrderState())) {
            throw new BusinessException(OrderErrorType.ORDER_NOT_WAIT_PAY);
        }
        setOrderState(OrderState.ABANDON);
    }

    public void valid() {
        setValidStatus(ValidStatus.VALID);
    }

    public void invalid() {
        setValidStatus(ValidStatus.INVALID);
    }
}
