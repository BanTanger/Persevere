package com.bantanger.elegant.service;

import com.bantanger.elegant.order.OrderModel;
import org.springframework.plugin.core.Plugin;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/6
 */
public interface IOrderModelHandler extends Plugin<OrderModel> {

    void handleOrderModel(OrderModel orderModel);

}
