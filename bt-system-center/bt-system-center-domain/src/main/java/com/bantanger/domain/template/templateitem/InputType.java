package com.bantanger.domain.template.templateitem;

/**
 * @author chensongmin
 * @description
 * @date 2025/2/7
 */

import com.bantanger.common.enums.BaseEnum;
import java.util.Optional;
import lombok.Getter;

@Getter
public enum InputType implements BaseEnum<InputType> {

    TEXT(1, "文本", "TextField"),
    TEXT_AREA(2, "多行文本", "TextareaField"),
    DATE(3, "日期", "DateField"),
    DATE_RANGE(4, "日期范围", "DateRangeField"),
    SINGLE_SELECT(5, "单选", "TextField"),
    MULTI_SELECT(6, "多选", "TextField"),
    MONEY_INPUT(7, "金额输入", "MoneyField"),
    DIGIT_INPUT(8, "数字输入", "NumberField"),
    FILE_INPUT(9, "文件输入", "FileField"),
    IMAGE_INPUT(10, "图片输入", "ImageField"),
    CITY_SELECT(11, "城市选择", "CityField"),

    ;

    InputType(Integer code, String name, String componentName) {
        this.code = code;
        this.name = name;
        this.componentName = componentName;
    }

    private final Integer code;
    private final String name;
    private final String componentName;

    public static Optional<InputType> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(InputType.class, code));
    }

}