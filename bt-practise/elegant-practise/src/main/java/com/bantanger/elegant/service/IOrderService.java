package com.bantanger.elegant.service;

import com.bantanger.elegant.order.OrderRequest;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/5
 */
public interface IOrderService {

    void handleOrder(OrderRequest orderRequest);

}
