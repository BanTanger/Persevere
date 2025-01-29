package com.bantanger.common.errortype;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/27
 */

import com.bantanger.common.enums.BaseEnum;
import java.util.Optional;
import lombok.Getter;

@Getter
public enum MessageErrorType implements BaseEnum<MessageErrorType> {

    TEMPLATE_NOT_FOUND(1, "模板不存在"),
    PARAMS_ERROR(2, "参数错误"),
    MESSAGE_SEND_FAST(3, "消息发送过于频繁"),

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
