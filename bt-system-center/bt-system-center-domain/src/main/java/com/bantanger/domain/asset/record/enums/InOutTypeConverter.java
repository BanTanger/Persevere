package com.bantanger.domain.asset.record.enums;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/22
 */
import jakarta.persistence.AttributeConverter;

public class InOutTypeConverter implements AttributeConverter<InOutType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(InOutType inOutType) {
        return inOutType.getCode();
    }

    @Override
    public InOutType convertToEntityAttribute(Integer code) {
        return InOutType.of(code).orElse(null);
    }
}
