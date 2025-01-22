package com.bantanger.domain.asset.record.enums;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/22
 */
import jakarta.persistence.AttributeConverter;

public class InOutBizTypeConverter implements AttributeConverter<InOutBizType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(InOutBizType inOutBizType) {
        return inOutBizType.getCode();
    }

    @Override
    public InOutBizType convertToEntityAttribute(Integer code) {
        return InOutBizType.of(code).orElse(null);
    }
}
