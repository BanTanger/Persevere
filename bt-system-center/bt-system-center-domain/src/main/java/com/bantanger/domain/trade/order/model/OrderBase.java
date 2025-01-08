package com.bantanger.domain.trade.order.model;

import com.bantanger.codegen.processor.api.GenCreateRequest;
import com.bantanger.codegen.processor.api.GenQueryRequest;
import com.bantanger.codegen.processor.api.GenResponse;
import com.bantanger.codegen.processor.api.GenUpdateRequest;
import com.bantanger.codegen.processor.controller.GenController;
import com.bantanger.codegen.processor.creator.GenCreator;
import com.bantanger.codegen.processor.creator.IgnoreCreator;
import com.bantanger.codegen.processor.mapper.GenEntityMapper;
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
import com.bantanger.common.constants.ValidStatus;
import com.bantanger.domain.CodeValue;
import com.bantanger.domain.CodeValueListConverter;
import com.bantanger.domain.pay.PayItem;
import com.bantanger.domain.pay.PayItemListConverter;
import com.bantanger.domain.trade.order.model.enums.OrderState;
import com.bantanger.domain.trade.order.model.enums.OrderStateConverter;
import com.bantanger.domain.trade.order.model.enums.OrderType;
import com.bantanger.domain.trade.order.model.enums.OrderTypeConverter;
import com.bantanger.domain.user.AccountType;
import com.bantanger.domain.user.AccountTypeConverter;
import com.bantanger.jpa.converter.ValidStatusConverter;
import com.bantanger.jpa.support.BaseJpaAggregate;
import com.bantanger.domain.GenSourceConstants;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
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
@GenEntityMapper(pkgName = "com.bantanger.domain.trade.order.mapper")
@GenMapper(pkgName = "com.bantanger.trigger.mapper.trade.order", sourcePath = GenSourceConstants.GEN_CONTROLLER_SOURCE)
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
    private AccountType accountType;

    // 根据不同的订单类型创建不同的状态机
    @FieldDesc(name = "订单类型: 1-普通订单, 2-秒杀订单")
    @Convert(converter = OrderTypeConverter.class)
    @TypeConverter(toTypeFullName = "java.lang.Integer")
    @IgnoreUpdater
    @QueryItem
    private OrderType orderType;

    @FieldDesc(name = "支付详情")
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

    public void init() {
        setValidStatus(ValidStatus.VALID);
    }

    public void valid() {
        setValidStatus(ValidStatus.VALID);
    }

    public void invalid() {
        setValidStatus(ValidStatus.INVALID);
    }
}
