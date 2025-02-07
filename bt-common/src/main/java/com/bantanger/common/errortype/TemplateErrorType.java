package com.bantanger.common.errortype;

/**
 * @author chensongmin
 * @description
 * @date 2025/2/7
 */

import com.bantanger.common.enums.BaseEnum;
import java.util.Optional;
import lombok.Getter;

@Getter
public enum TemplateErrorType implements BaseEnum<TemplateErrorType> {

    INPUT_TOO_BIG(1401001, "输入值过大"),
    INPUT_TOO_SMALL(1401002,"输入值过小"),
    FORMAT_ERROR(1401003,"格式错误"),
    INPUT_TOO_LONG(1401004,"输入文字太长"),
    CODE_EXIST(1401005,"编码已经存在")

    ;

    TemplateErrorType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private final Integer code;
    private final String name;

    public static Optional<TemplateErrorType> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(TemplateErrorType.class, code));
    }

}
