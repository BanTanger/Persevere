package com.bantanger.domain.stock.seat.enums;

import com.bantanger.common.enums.BaseEnum;
import lombok.Getter;

import java.util.Optional;

/**
 * @author chensongmin
 * @created 2025/3/6
 */

@Getter
public enum SeatType implements BaseEnum<SeatType> {

    SEAT_NORMAL(1, "普通座位"),
    SEAT_LOVER_L(2, "情侣座位-左"),
    SEAT_LOVER_R(3, "情侣座位-右"),
    ;

    SeatType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private final Integer code;
    private final String name;

    public static Optional<SeatType> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(SeatType.class, code));
    }

}