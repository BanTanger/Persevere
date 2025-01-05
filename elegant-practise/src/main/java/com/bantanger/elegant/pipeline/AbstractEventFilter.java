package com.bantanger.elegant.pipeline;

import com.bantanger.elegant.pipeline.context.EventContext;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chensongmin
 * @description 基层事件过滤器模版封装, 交由子类继承实现不同定制化 handle 逻辑
 * @create 2025/1/4
 */
@Slf4j
public abstract class AbstractEventFilter<T extends EventContext> implements EventFilter<T> {

    @Override
    public void doFilter(T context, EventFilterChain<T> chain) {
        log.info("业务模块：{}, filter 逻辑处理...", context.getBizCode());
        if (context.getFilterSelector().matchFilter(this.getClass().getSimpleName())) {
            handle(context);
        }
        if (context.continueChain()) {
            chain.fireNext(context);
        }
    }

    /**
     * 子类实现的具体 handle 处理判断逻辑
     * @param context 上下文对象
     */
    protected abstract void handle(T context);

}
