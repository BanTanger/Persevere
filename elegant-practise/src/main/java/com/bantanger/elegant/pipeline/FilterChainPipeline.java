package com.bantanger.elegant.pipeline;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/4
 */
@SuppressWarnings("all")
public class FilterChainPipeline<T extends EventFilter> {

    private DefaultFilterChain last;

    public FilterChainPipeline() {
    }

    public DefaultFilterChain getFilterChain() {
        return this.last;
    }

    public FilterChainPipeline addFirst(T filter) {
        DefaultFilterChain newChain = new DefaultFilterChain<>(this.last, filter);
        this.last = newChain;
        return this;
    }

    // todo 可扩展节点描述，供序列化反序列化使用
    public FilterChainPipeline addFirst(T filter, String desc) {
        DefaultFilterChain newChain = new DefaultFilterChain<>(this.last, filter);
        this.last = newChain;
        return this;
    }

}
