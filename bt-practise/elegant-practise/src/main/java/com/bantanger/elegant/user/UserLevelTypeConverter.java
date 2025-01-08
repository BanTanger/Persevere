package com.bantanger.elegant.user;

import jakarta.persistence.AttributeConverter;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/5
 */
public class UserLevelTypeConverter implements AttributeConverter<UserLevelType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(UserLevelType userLevelType) {
        return userLevelType.getCode();
    }

    @Override
    public UserLevelType convertToEntityAttribute(Integer code) {
        return UserLevelType.of(code).orElse(null);
    }
}
