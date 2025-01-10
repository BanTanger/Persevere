package com.bantanger.domain.trade.order.events;

import com.bantanger.domain.trade.order.events.OrderEvents.OrderCreateEvent;
import com.bantanger.domain.trade.orderitem.creator.OrderItemCreator;
import com.bantanger.domain.trade.orderitem.mapper.OrderItemMapper;
import com.bantanger.domain.trade.orderitem.service.IOrderItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/9
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class OrderEventProcessor {

    private final IOrderItemService orderItemService;

    /**
     * 订单创建通知订单项子域
     * @param orderCreateEvent
     */
    @EventListener
    public void handleOrderCreateForItem(OrderCreateEvent orderCreateEvent){
        log.info("handleOrderCreateForItem");
        orderCreateEvent.getCreateModel().getItemInfoList()
            .forEach(model -> {
                // 创建订单项
                OrderItemCreator creator = OrderItemMapper.INSTANCE.model2Creator(model);
                creator.setOrderId(orderCreateEvent.orderBase().getId());
                creator.setFlowNo(orderCreateEvent.orderBase().getFlowNo());
                orderItemService.createOrderItem(creator);
            });
    }

    @EventListener
    public void handleOrderCreateForEs(OrderCreateEvent createEvent){
        log.info("handleOrderCreateForEs");
    }

    /**
     * 订单创建通知订单生命周期子域
     * @param createEvent
     */
    @EventListener
    public void handleOrderCreateForLifecycle(OrderCreateEvent createEvent){
        log.info("handleOrderCreateForLifecycle");
    }

}
