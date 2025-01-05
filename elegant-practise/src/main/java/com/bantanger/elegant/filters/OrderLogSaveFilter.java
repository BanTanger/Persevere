package com.bantanger.elegant.filters;

import com.bantanger.elegant.order.OrderContext;
import com.bantanger.elegant.pipeline.AbstractEventFilter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/5
 */
@Slf4j
@RequiredArgsConstructor
public class OrderLogSaveFilter extends AbstractEventFilter<OrderContext> {

    // private final MsgProduct msgProduct;

    @Override
    protected void handle(OrderContext context) {
        // 报表类逻辑处理，用户行为增长
        log.info("下单操作日志存储");
        // MQ 异步落盘
        // msgProduct.send("");
    }

}
