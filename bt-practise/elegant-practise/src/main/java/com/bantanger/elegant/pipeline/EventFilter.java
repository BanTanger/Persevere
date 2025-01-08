package com.bantanger.elegant.pipeline;


import com.bantanger.elegant.pipeline.context.EventContext;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/4
 */
public interface EventFilter<T extends EventContext> {

    /**
     * 过滤逻辑封装点
     * @param context 上层透传的上下文对象
     * @param chain 责任链负责事件处理与开启下一个鉴权
     */
    void doFilter(T context, EventFilterChain<T> chain);

}
