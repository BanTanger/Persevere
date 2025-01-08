package com.bantanger.elegant.pipeline.context;

import com.bantanger.elegant.pipeline.BizEnum;
import com.bantanger.elegant.pipeline.selector.FilterSelector;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/4
 */
public interface EventContext {

    /**
     * 获取业务编码
     * @return
     */
    BizEnum getBizCode();

    /**
     * 获取业务过滤器选择器
     * @return
     */
    FilterSelector getFilterSelector();

    /**
     * 是否继续责任链执行？
     * @return
     */
    boolean continueChain();
}
