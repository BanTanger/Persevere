package com.bantanger.elegant.pipeline.context;

import com.bantanger.elegant.pipeline.BizEnum;
import com.bantanger.elegant.pipeline.selector.FilterSelector;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/4
 */
public abstract class AbstractEventContext implements EventContext {

    private final BizEnum bizEnum;
    private final FilterSelector selector;

    public AbstractEventContext(BizEnum bizEnum, FilterSelector selector) {
        this.bizEnum = bizEnum;
        this.selector = selector;
    }

    @Override
    public BizEnum getBizCode() {
        return bizEnum;
    }

    @Override
    public FilterSelector getFilterSelector() {
        return selector;
    }

}
