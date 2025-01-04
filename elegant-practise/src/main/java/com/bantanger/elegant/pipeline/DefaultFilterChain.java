package com.bantanger.elegant.pipeline;

import com.bantanger.elegant.pipeline.context.EventContext;

import java.util.Objects;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/4
 */
public class DefaultFilterChain<T extends EventContext> implements EventFilterChain<T> {

    private EventFilterChain<T> next;
    private EventFilter<T> filter;

    public DefaultFilterChain(EventFilterChain<T> next, EventFilter<T> filter) {
        this.next = next;
        this.filter = filter;
    }

    @Override
    public void handle(T context) {
        filter.doFilter(context, this);
    }

    @Override
    public void fireNext(T context) {
        EventFilterChain<T> nextChain = this.next;
        if (Objects.nonNull(nextChain)) {
            nextChain.handle(context);
        }
    }

}
