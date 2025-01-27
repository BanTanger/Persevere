package com.bantanger.extension.executor;

/**
 * @author chensongmin
 * @description 业务场景
 * @date 2025/1/27
 */
public sealed interface BizScene permits BizEnum {

    /**
     * 获取业务场景ID，bizId 必须唯一，BizScene 约束用枚举继承
     * @return
     */
    String getBizId();
}
