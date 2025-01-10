package com.bantanger.jpa.converter;

import java.time.Instant;
import javax.persistence.AttributeConverter;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/8
 */
public class InstantLongConverter implements AttributeConverter<Instant, Long> {

    @Override
    public Long convertToDatabaseColumn(Instant date) {
        return date == null ? null : date.toEpochMilli();
    }

    @Override
    public Instant convertToEntityAttribute(Long date) {
        return date == null ? null : Instant.ofEpochMilli(date);
    }
}