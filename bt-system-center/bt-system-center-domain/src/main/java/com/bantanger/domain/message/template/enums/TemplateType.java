package com.bantanger.domain.message.template.enums;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/27
 */

import com.bantanger.common.enums.BaseEnum;
import java.util.Optional;
import lombok.Getter;

@Getter
public enum TemplateType implements BaseEnum<TemplateType> {

    SMS(1, "短信"),
    EMAIL(2,"邮件"),
    MARKDOWN(3,"markdown")

    ;

    TemplateType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private final Integer code;
    private final String name;

    public static Optional<TemplateType> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(TemplateType.class, code));
    }

}
