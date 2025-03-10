package com.bantanger.domain.stock.seat.enums;

/**
 * @author chensongmin
 * @created 2025/3/6
 */

import jakarta.persistence.AttributeConverter;

public class SeatTypeConverter implements AttributeConverter<SeatType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(SeatType seatType) {
        return seatType.getCode();
    }

    @Override
    public SeatType convertToEntityAttribute(Integer code) {
        return SeatType.of(code).orElse(null);
    }
}