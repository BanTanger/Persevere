package com.bantanger.domain.trade.order.enums;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/8
 */
import com.bantanger.common.constants.BaseEnum;
import java.util.Optional;
import lombok.Getter;

@Getter
public enum OrderState implements BaseEnum<OrderState> {

    WAIT_PAY(1, "待支付"),
    PAY_SUCCESS(2, "支付完成"),
    ABANDON(3, "已废弃")

    ;

    OrderState(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private final Integer code;
    private final String name;

    public static Optional<OrderState> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(OrderState.class, code));
    }

}
