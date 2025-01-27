package com.bantanger.domain.message.record.enums;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/27
 */

import com.bantanger.common.constants.BaseEnum;
import java.util.Optional;
import lombok.Getter;

@Getter
public enum MsgType implements BaseEnum<MsgType> {

    VERIFY(1, "验证码"),
    NOTICE(2,"通知"),

    ;

    MsgType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private final Integer code;
    private final String name;

    public static Optional<MsgType> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(MsgType.class, code));
    }

}
