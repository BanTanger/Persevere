package com.bantanger.domain.message.record.enums;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/27
 */
import jakarta.persistence.AttributeConverter;

public class NotifyTypeConverter implements AttributeConverter<NotifyType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(NotifyType notifyType) {
        return notifyType.getCode();
    }

    @Override
    public NotifyType convertToEntityAttribute(Integer code) {
        return NotifyType.of(code).orElse(null);
    }
}
