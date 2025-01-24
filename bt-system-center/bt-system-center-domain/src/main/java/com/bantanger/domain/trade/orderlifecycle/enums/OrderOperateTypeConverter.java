package com.bantanger.domain.trade.orderlifecycle.enums;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/24
 */
import jakarta.persistence.AttributeConverter;

public class OrderOperateTypeConverter implements AttributeConverter<OrderOperateType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(OrderOperateType orderOperateType) {
        return orderOperateType.getCode();
    }

    @Override
    public OrderOperateType convertToEntityAttribute(Integer code) {
        return OrderOperateType.of(code).orElse(null);
    }
}
