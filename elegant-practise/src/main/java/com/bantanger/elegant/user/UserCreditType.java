package com.bantanger.elegant.user;

import com.bantanger.commons.constants.BaseEnum;

import java.util.Optional;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/5
 */
public enum UserCreditType implements BaseEnum<UserCreditType> {

    // 普通用户
    CREDIT_NORMAL(1, "normal"),
    // 优质用户
    CREDIT_SUPERIOR(2, "superior"),
    // 风险用户，上黑名单
    CREDIT_RISK(3, "risk"),
    ;

    UserCreditType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private Integer code;
    private String name;

    @Override
    public Integer getCode() {
        return this.code;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public static Optional<UserCreditType> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(UserCreditType.class, code));
    }

}
