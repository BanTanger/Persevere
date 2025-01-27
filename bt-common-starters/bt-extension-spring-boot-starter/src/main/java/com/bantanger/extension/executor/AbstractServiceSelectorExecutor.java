package com.bantanger.extension.executor;

import java.util.function.Consumer;
import java.util.function.Function;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/27
 */
public abstract class AbstractServiceSelectorExecutor implements ServiceExecutor {

    /**
     * 根据 BizScene 业务场景，查询对应 Service 服务
     * @param bizScene 业务场景，实为枚举
     * @param clazz 接口 Class
     * @return
     * @param <S>
     */
    protected abstract <S> S selectBizScene(BizScene bizScene, Class<S> clazz);

    /**
     * 执行 void 方法
     *
     * @param clazz
     * @param bizScene
     * @param consumer
     * @param <S>
     */
    @Override
    public <S> void execute(Class<S> clazz, BizScene bizScene, Consumer<S> consumer) {
        S service = selectBizScene(bizScene, clazz);
        consumer.accept(service);
    }

    /**
     * 执行有返回值的方法
     *
     * @param clazz
     * @param bizScene
     * @param function
     * @param <R>
     * @param <S>
     * @return
     */
    @Override
    public <R, S> R execute(Class<S> clazz, BizScene bizScene, Function<S, R> function) {
        S service = selectBizScene(bizScene, clazz);
        return function.apply(service);
    }
}
