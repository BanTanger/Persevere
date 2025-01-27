package com.bantanger.domain.message.template.enums;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/27
 */
import jakarta.persistence.AttributeConverter;

public class TemplateTypeConverter implements AttributeConverter<TemplateType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(TemplateType templateType) {
        return templateType.getCode();
    }

    @Override
    public TemplateType convertToEntityAttribute(Integer code) {
        return TemplateType.of(code).orElse(null);
    }
}
