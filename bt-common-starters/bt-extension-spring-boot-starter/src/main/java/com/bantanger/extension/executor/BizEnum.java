package com.bantanger.extension.executor;

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

}
