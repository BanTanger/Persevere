package com.bantanger.domain.template.templateitem;

/**
 * @author chensongmin
 * @description
 * @date 2025/2/7
 */

import jakarta.persistence.AttributeConverter;

public class InputTypeConverter implements AttributeConverter<InputType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(InputType inputType) {
        return inputType.getCode();
    }

    @Override
    public InputType convertToEntityAttribute(Integer code) {
        return InputType.of(code).orElse(null);
    }
}
