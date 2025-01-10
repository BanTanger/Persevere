package com.bantanger.domain.user;

import javax.persistence.AttributeConverter;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/8
 */

public class AccountTypeConverter implements AttributeConverter<AccountType, Integer> {

    @Override
    public Integer convertToDatabaseColumn(AccountType accountType) {
        return accountType.getCode();
    }

    @Override
    public AccountType convertToEntityAttribute(Integer code) {
        return AccountType.of(code).orElse(null);
    }
}
