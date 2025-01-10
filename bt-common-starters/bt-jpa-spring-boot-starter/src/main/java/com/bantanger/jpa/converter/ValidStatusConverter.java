package com.bantanger.jpa.converter;

import com.bantanger.common.constants.ValidStatus;
import javax.persistence.AttributeConverter;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/8
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
