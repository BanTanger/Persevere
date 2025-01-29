package com.bantanger.domain.trade.order.enums;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/8
 */
import com.bantanger.common.enums.BaseEnum;
import java.util.Optional;
import lombok.Getter;

@Getter
public enum OrderType implements BaseEnum<OrderType> {

    CHARGE(1, "充电"),
    PARK(2, "停车"),
    SHOP(3, "商城"),
//    MOVIE_TICKETS(4, "电影票"),

    ;

    OrderType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private final Integer code;
    private final String name;

    public static Optional<OrderType> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(OrderType.class, code));
    }

}
