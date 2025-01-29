package com.bantanger.domain.message.record.enums;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/27
 */

import com.bantanger.common.enums.BaseEnum;
import java.util.Optional;
import lombok.Getter;

@Getter
public enum NotifyType implements BaseEnum<NotifyType> {

    SMS(1, "短信通知"),
    EMAIL(2,"邮件通知"),
    DING_DING(3,"钉钉通知"),
    WECHAT(4,"微信公众号通知"),
    FEI_SHU(5,"飞书通知"),

    ;

    NotifyType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private final Integer code;
    private final String name;

    public static Optional<NotifyType> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(NotifyType.class, code));
    }

}
