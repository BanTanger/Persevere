package com.bantanger.types.enums;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/9
 */

import com.bantanger.common.constants.BaseEnum;
import java.util.Optional;
import lombok.Getter;

@Getter
public enum OrderErrorEnum implements BaseEnum<OrderErrorEnum> {

    PAY_TOO_BIG(1101001, "支付金额过大，系统异常"),

    ORDER_NOT_WAIT_PAY(1101002, "订单不是待支付状态"),

    PAY_LIST_IS_NULL(1101003, "支付列表不能为空"),

    PAY_AMOUNT_NOT_EQUAL_WAIT_PAY(1101004, "支付金额与待支付金额不等");

    OrderErrorEnum(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private final Integer code;
    private final String name;

    public static Optional<OrderErrorEnum> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(OrderErrorEnum.class, code));
    }

}
