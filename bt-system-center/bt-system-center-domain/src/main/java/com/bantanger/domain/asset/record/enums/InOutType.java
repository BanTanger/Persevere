package com.bantanger.domain.asset.record.enums;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/22
 */

import com.bantanger.common.constants.BaseEnum;
import java.util.Optional;
import lombok.Getter;

@Getter
public enum InOutType implements BaseEnum<InOutType> {

    IN(1, "入库"),
    OUT(2, "出库"),

    ;

    InOutType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private final Integer code;
    private final String name;

    public static Optional<InOutType> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(InOutType.class, code));
    }

}
