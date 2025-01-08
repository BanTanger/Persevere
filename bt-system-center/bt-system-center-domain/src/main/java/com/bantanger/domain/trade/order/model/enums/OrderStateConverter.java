package com.bantanger.domain.trade.order.model.enums;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/8
 */
import jakarta.persistence.AttributeConverter;

public class OrderStateConverter implements AttributeConverter<OrderState, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrderState orderState) {
        return orderState.getCode();
    }

    @Override
    public OrderState convertToEntityAttribute(Integer code) {
        return OrderState.of(code).orElse(null);
    }
}
