package com.bantanger.commons.jpa.converter;

import com.bantanger.commons.constants.ValidStatus;
import jakarta.persistence.AttributeConverter;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/5
 */
public class ValidStatusConverter implements AttributeConverter<ValidStatus, Integer> {
    @Override
    public Integer convertToDatabaseColumn(ValidStatus validStatus) {
        return validStatus.getCode();
    }

    @Override
    public ValidStatus convertToEntityAttribute(Integer code) {
        return ValidStatus.of(code).orElse(null);
    }
}
