package com.bantanger.domain.trade.order.model.enums;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/8
 */
import jakarta.persistence.AttributeConverter;

public class OrderTypeConverter implements AttributeConverter<OrderType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrderType orderType) {
        return orderType.getCode();
    }

    @Override
    public OrderType convertToEntityAttribute(Integer code) {
        return OrderType.of(code).orElse(null);
    }
}
