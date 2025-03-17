/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */

package com.bantanger.common.errortype;

import com.bantanger.common.enums.BaseEnum;
import lombok.Getter;

import java.util.Optional;

/**
 * @author chensongmin02
 * @created 2025/3/14
 */

@Getter
public enum SeatStockErrorType implements BaseEnum<SeatStockErrorType> {

    SEAT_STOCK_ALREADY_LOCKED_CACHE(1501001, "座位库存已被预占-缓存分布式锁"),
    SEAT_STOCK_ALREADY_LOCKED_DB(1501002, "座位库存已被预占-数据库乐观锁"),
    SEAT_STOCK_ALREADY_SOLD_CACHE(1501003, "座位库存已被售出-缓存分布式锁"),
    SEAT_STOCK_ALREADY_SOLD_DB(1501004, "座位库存已被售出-数据库乐观锁")

    ;

    SeatStockErrorType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private final Integer code;
    private final String name;

    public static Optional<SeatStockErrorType> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(SeatStockErrorType.class, code));
    }

}