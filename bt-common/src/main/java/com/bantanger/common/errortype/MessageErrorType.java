package com.bantanger.common.errortype;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/27
 */

import com.bantanger.common.enums.BaseEnum;
import lombok.Getter;

import java.util.Optional;

@Getter
public enum MessageErrorType implements BaseEnum<MessageErrorType> {

    TEMPLATE_NOT_FOUND(1101001, "模板不存在"),
    PARAMS_ERROR(1101002, "参数错误"),
    MESSAGE_SEND_FAST(1101003, "消息发送过于频繁"),

    ;

    MessageErrorType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private final Integer code;
    private final String name;

    public static Optional<MessageErrorType> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(MessageErrorType.class, code));
    }

}
