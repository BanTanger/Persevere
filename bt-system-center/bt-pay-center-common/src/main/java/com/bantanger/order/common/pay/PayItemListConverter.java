package com.bantanger.order.common.pay;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import javax.persistence.AttributeConverter;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/8
 */
@Slf4j
public class PayItemListConverter implements AttributeConverter<List<PayItem>, String> {

    @Override
    public String convertToDatabaseColumn(List<PayItem> payItems) {
        return Try.of(() -> new ObjectMapper().writeValueAsString(payItems))
            .onFailure(e -> log.error("convertToDatabaseColumn json writing error", e))
            .getOrNull();
    }

    @Override
    public List<PayItem> convertToEntityAttribute(String s) {
        return Try.of(() -> new ObjectMapper().readValue(s.toString(),
                new TypeReference<List<PayItem>>() {}))
            .onFailure(e -> log.error("convertToEntityAttribute json reading error", e))
            .getOrNull();
    }
}
