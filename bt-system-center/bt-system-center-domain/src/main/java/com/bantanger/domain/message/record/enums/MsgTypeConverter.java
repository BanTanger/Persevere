package com.bantanger.domain.message.record.enums;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/27
 */
import jakarta.persistence.AttributeConverter;

public class MsgTypeConverter implements AttributeConverter<MsgType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(MsgType msgType) {
        return msgType.getCode();
    }

    @Override
    public MsgType convertToEntityAttribute(Integer code) {
        return MsgType.of(code).orElse(null);
    }
}

