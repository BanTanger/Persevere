package com.bantanger.domain.trade.order.events;

import com.bantanger.domain.trade.order.domainservice.model.OrderCompleteModel;
import com.bantanger.domain.trade.order.domainservice.model.OrderCreateModel;
import com.bantanger.domain.trade.order.domainservice.model.OrderReviseModel;
import com.bantanger.domain.trade.order.OrderBase;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/8
 */
public interface OrderEvents {

    /**
     * 订单创建事件
     * @param orderBase
     * @param orderCreateModel
     */
    record OrderCreateEvent(OrderBase orderBase, OrderCreateModel orderCreateModel) {}

    /**
     * 订单完成事件
     * @param orderBase
     * @param orderCompleteModel
     */
    record OrderCompleteEvent(OrderBase orderBase, OrderCompleteModel orderCompleteModel) {}

    /**
     * 订单修订事件
     * @param orderBase
     * @param orderReviseModel
     */
    record OrderReviseEvent(OrderBase orderBase, OrderReviseModel orderReviseModel) {}

    /**
     * 订单取消事件
     * @param orderBase
     */
    record OrderCancelEvent(OrderBase orderBase) {}
}
