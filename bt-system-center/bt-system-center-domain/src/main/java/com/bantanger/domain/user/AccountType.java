package com.bantanger.domain.user;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/8
 */

import com.bantanger.common.enums.BaseEnum;
import java.util.Optional;
import lombok.Getter;

@Getter
public enum AccountType implements BaseEnum<AccountType> {

    PERSONAL(1, "个人"),

    CORP(2, "企业"),

    ;

    AccountType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private final Integer code;
    private final String name;

    public static Optional<AccountType> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(AccountType.class, code));
    }

}