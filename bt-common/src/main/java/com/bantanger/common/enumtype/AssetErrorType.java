package com.bantanger.common.enumtype;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/15
 */

import com.bantanger.common.constants.BaseEnum;
import java.util.Optional;
import lombok.Getter;

@Getter
public enum AssetErrorType implements BaseEnum<AssetErrorType> {

    ASSET_HAS_IN(10010026, "资产已入库"),

    ASSET_HAS_OUT(10010027, "资产已出库"),

    ASSET_CODE_NOT_EXIST(10010028, "资产编码不存在")

    ;

    AssetErrorType(Integer code, String name) {
        this.code = code;
        this.name = name;
    }

    private final Integer code;
    private final String name;

    public static Optional<AssetErrorType> of(Integer code) {
        return Optional.ofNullable(BaseEnum.parseByCode(AssetErrorType.class, code));
    }

}