package com.bantanger.domain.template.selectdict;

/**
 * @author chensongmin
 * @description
 * @date 2025/2/7
 */

import com.bantanger.common.enums.BaseEnum;
import java.util.Optional;
import lombok.Getter;

@Getter
public enum DictType implements BaseEnum<DictType> {

    SELECT_LIST(1, "下拉选择列表"),
    HTTP_INVOKE(2, "HTTP接口调用填值")

    ;

    DictType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private final Integer code;
    private final String name;

    public static Optional<DictType> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(DictType.class, code));
    }

}
