package com.bantanger.common.converter;

/**
 * @author chensongmin
 * @description
 * @date 2025/2/7
 */

import com.bantanger.common.verifyrule.VerifyRule;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.vavr.control.Try;
import jakarta.persistence.AttributeConverter;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class VerifyRuleListConverter implements AttributeConverter<List<VerifyRule>, String> {

    @Override
    public String convertToDatabaseColumn(List<VerifyRule> verifyRuleList) {
        return Try.of(() -> new ObjectMapper().writeValueAsString(verifyRuleList))
            .onFailure(e -> log.error("convertToDatabaseColumn json writing error"))
            .getOrNull();
    }

    @Override
    public List<VerifyRule> convertToEntityAttribute(String o) {
        return Try.of(() -> new ObjectMapper().readValue(o,
                new TypeReference<List<VerifyRule>>() {}))
            .onFailure(e -> log.error("convertToEntityAttribute json reading error"))
            .getOrNull();
    }
}
