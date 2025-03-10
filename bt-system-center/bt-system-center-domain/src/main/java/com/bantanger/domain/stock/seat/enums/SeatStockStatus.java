package com.bantanger.domain.stock.seat.enums;

import com.bantanger.common.enums.BaseEnum;
import lombok.Getter;

import java.util.Optional;

/**
 * @author chensongmin
 * @created 2025/3/6
 */

@Getter
public enum SeatStockStatus implements BaseEnum<SeatStockStatus> {

    STOCK_LOCK(1, "库存已锁"),
    STOCK_KEEP(2, "库存可选"),
    STOCK_SOLD(3, "库存已售"),
    ;

    SeatStockStatus(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private final Integer code;
    private final String name;

    public static Optional<SeatStockStatus> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(SeatStockStatus.class, code));
    }

}