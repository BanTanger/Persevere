package com.bantanger.elegant.pipeline;

import com.bantanger.elegant.pipeline.context.EventContext;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/4
 */
public interface EventFilterChain<T extends EventContext> {

    /**
     * 进行事件处理逻辑
     * @param context
     */
    void handle(T context);

    /**
     * 开启下一个鉴权
     * @param context
     */
    void fireNext(T context);

}
