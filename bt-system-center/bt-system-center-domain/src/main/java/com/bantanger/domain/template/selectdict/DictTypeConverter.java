package com.bantanger.domain.template.selectdict;

/**
 * @author chensongmin
 * @description
 * @date 2025/2/7
 */
import jakarta.persistence.AttributeConverter;

public class DictTypeConverter implements AttributeConverter<DictType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(DictType dictType) {
        return dictType.getCode();
    }

    @Override
    public DictType convertToEntityAttribute(Integer code) {
        return DictType.of(code).orElse(null);
    }
}

