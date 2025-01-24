package com.bantanger.domain.trade.order.events;

import com.bantanger.domain.trade.order.events.OrderEvents.OrderCreateEvent;
import com.bantanger.domain.trade.orderitem.creator.OrderItemCreator;
import com.bantanger.domain.trade.orderitem.mapper.OrderItemMapper;
import com.bantanger.domain.trade.orderitem.service.IOrderItemService;
import com.bantanger.domain.trade.orderlifecycle.creator.OrderLifecycleCreator;
import com.bantanger.domain.trade.orderlifecycle.enums.OrderOperateType;
import com.bantanger.domain.trade.orderlifecycle.service.IOrderLifecycleService;
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
    private final IOrderLifecycleService orderLifecycleService;

    /**
     * 订单创建通知订单项子域
     * @param orderCreateEvent
     */
    @EventListener
    public void handleOrderCreateForItem(OrderCreateEvent orderCreateEvent){
        log.info("handleOrderCreateForItem");
        orderCreateEvent.orderCreateModel().getItemInfoList()
            .forEach(model -> {
                // 创建订单项
                OrderItemCreator creator = OrderItemMapper.INSTANCE.model2Creator(model);
                creator.setOrderId(orderCreateEvent.orderBase().getId());
                creator.setFlowNo(orderCreateEvent.orderBase().getFlowNo());
                orderItemService.createOrderItem(creator);
            });
    }

    /**
     * 订单创建通知，数据同步 ES 【查存分离 CQRS】
     * @param createEvent
     */
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
        OrderLifecycleCreator creator = new OrderLifecycleCreator();
        creator.setFlowNo(createEvent.orderBase().getFlowNo());
        creator.setOperateType(OrderOperateType.ORDER_CREATE);
        creator.setOperateUser(createEvent.orderCreateModel().getOperateUser());
        creator.setRemark("订单创建成功");
        orderLifecycleService.createOrderLifecycle(creator);
    }

}
