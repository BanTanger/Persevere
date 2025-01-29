package com.bantanger.domain.trade.orderlifecycle.enums;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/24
 */

import com.bantanger.common.enums.BaseEnum;
import java.util.Optional;
import lombok.Getter;

@Getter
public enum OrderOperateType implements BaseEnum<OrderOperateType> {

    ORDER_CREATE(1, "订单创建"),
    PAY_SUCCESS(2, "支付成功"),
    REVISE(3, "修订完成"),
    AUTH_SUCCESS(4, "鉴权成功"),
    AUTH_START(5, "鉴权请求开始"),
    AUTH_FAIL(6, "鉴权失败"),
    START_SUCCESS(7, "启动成功"),
    START_FAIL(8, "启动失败")
    ;

    OrderOperateType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private final Integer code;
    private final String name;

    public static Optional<OrderOperateType> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(OrderOperateType.class, code));
    }

}
