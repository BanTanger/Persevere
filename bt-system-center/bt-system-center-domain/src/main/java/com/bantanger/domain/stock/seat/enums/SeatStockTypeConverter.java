package com.bantanger.domain.stock.seat.enums;

/**
 * @author chensongmin
 * @created 2025/3/6
 */

import jakarta.persistence.AttributeConverter;

public class SeatStockTypeConverter implements AttributeConverter<SeatStockStatus, Integer> {

    @Override
    public Integer convertToDatabaseColumn(SeatStockStatus seatStockStatus) {
        return seatStockStatus.getCode();
    }

    @Override
    public SeatStockStatus convertToEntityAttribute(Integer code) {
        return SeatStockStatus.of(code).orElse(null);
    }
}