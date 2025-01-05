package com.bantanger.elegant.service.impl;

import com.bantanger.elegant.order.OrderContext;
import com.bantanger.elegant.order.OrderRequest;
import com.bantanger.elegant.order.UserModel;
import com.bantanger.elegant.pipeline.BizEnum;
import com.bantanger.elegant.pipeline.EventFilter;
import com.bantanger.elegant.pipeline.FilterChainPipeline;
import com.bantanger.elegant.selector.OrderFilterSelectorFactory;
import com.bantanger.elegant.service.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/5
 */
@SuppressWarnings("unchecked")
@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {

    private final FilterChainPipeline<EventFilter> orderPipeline;
    // 通过 selectorFactory 依靠配置文件动态生成 selector 选择器
    private final OrderFilterSelectorFactory envBasedFilterSelectorFactory;
    @Override
    public void handleOrder(OrderRequest orderRequest) {
        OrderContext orderContext = new OrderContext(BizEnum.METRIC_EVENT,
                envBasedFilterSelectorFactory.getFilterSelector(orderRequest));
        orderContext.setOrderRequest(orderRequest);
        // 通用业务逻辑处理
        orderPipeline.getFilterChain().handle(orderContext);
        // todo 扩展点实现
//        UserModel userModel = orderContext.getUserModel();
    }
}
