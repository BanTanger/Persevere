package com.bantanger.extension.executor;

import com.bantanger.common.constants.BaseEnum;
import java.util.stream.Stream;

/**
 * @author chensongmin
 * @description 业务枚举封装接口，业务枚举必须实现该接口
 * @date 2025/1/27
 */
public non-sealed interface BizEnum extends BizScene {

    /**
     * 获取业务ID
     * @return
     */
    String getBizId();

    /**
     * 获取业务描述
     * @return
     */
    String getDesc();

    /**
     * 根据 code 码获取业务枚举
     * @param enumClazz 枚举对象
     * @param code 枚举code码
     * @return 具体枚举值
     */
    static <T extends Enum<T> & BaseEnum<T>> T parseByCode(Class<T> enumClazz, Integer code) {
        return Stream.of(enumClazz.getEnumConstants())
            .filter(t -> t.getCode().intValue() == code.intValue())
            .findFirst()
            .orElse(null);
    }
}
