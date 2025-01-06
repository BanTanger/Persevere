package com.bantanger.elegant.order;

import com.bantanger.elegant.pipeline.BizEnum;
import com.bantanger.elegant.pipeline.context.AbstractEventContext;
import com.bantanger.elegant.pipeline.selector.FilterSelector;
import lombok.Getter;
import lombok.Setter;

/**
 * @author chensongmin
 * @description 下单统一上下文
 * @create 2025/1/5
 */
@Getter
@Setter
public class OrderContext extends AbstractEventContext {

    /**
     * ================// 请求 //====================
     */
    private OrderRequest orderRequest;
    /**
     * ================// 编程模型 //====================
     */
    private OrderModel orderModel;

    public OrderContext(BizEnum bizEnum, FilterSelector selector) {
        super(bizEnum, selector);
    }

    @Override
    public boolean continueChain() {
        return true;
    }
}
