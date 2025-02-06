package com.bantanger.domain.permission.resource;

/**
 * @author chensongmin
 * @description
 * @date 2025/2/6
 */

import com.bantanger.common.enums.BaseEnum;
import java.util.Optional;
import lombok.Getter;

@Getter
public enum ResourceType implements BaseEnum<ResourceType> {

    MODULE(1, "模块"),
    MENU(2, "菜单"),
    FUNC(3, "功能"),

    ;

    ResourceType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private final Integer code;
    private final String name;

    public static Optional<ResourceType> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(ResourceType.class, code));
    }

}
