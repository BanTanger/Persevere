package com.bantanger.elegant.user;

import javax.persistence.AttributeConverter;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/5
 */
public class UserCreditTypeConverter implements AttributeConverter<UserCreditType, Integer> {
    @Override
    public Integer convertToDatabaseColumn(UserCreditType userCreditType) {
        return userCreditType.getCode();
    }

    @Override
    public UserCreditType convertToEntityAttribute(Integer code) {
        return UserCreditType.of(code).orElse(null);
    }
}
