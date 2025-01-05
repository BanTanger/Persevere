package com.bantanger.elegant.filters;

import com.bantanger.elegant.order.OrderContext;
import com.bantanger.elegant.pipeline.AbstractEventFilter;
import com.bantanger.elegant.service.facade.IOrderPipelineFacadeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/5
 */
@Slf4j
@RequiredArgsConstructor
public class UserRiskFilter extends AbstractEventFilter<OrderContext> {

    private final IOrderPipelineFacadeService orderPipelineFacadeService;
    @Override
    protected void handle(OrderContext context) {

    }
}
