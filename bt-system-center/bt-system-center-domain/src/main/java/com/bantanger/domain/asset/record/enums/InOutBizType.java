package com.bantanger.domain.asset.record.enums;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/15
 */

import com.bantanger.common.enums.BaseEnum;
import java.util.Optional;
import lombok.Getter;

@Getter
public enum InOutBizType implements BaseEnum<InOutBizType> {

    IN_INITIAL(1, "初始入库"),

    IN_TRANSFER(2, "调拨入库"),

    OUT_TRANSFER(3, "调拨出库"),

    IN_BUY(4, "购买入库"),

    OUT_SALE(5, "销售出库")

    ;

    InOutBizType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private final Integer code;
    private final String name;

    public static Optional<InOutBizType> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(InOutBizType.class, code));
    }

}
