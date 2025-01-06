package com.bantanger.elegant.service.impl;

import com.bantanger.elegant.order.OrderModel;
import com.bantanger.elegant.service.IOrderModelHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/6
 */
@Slf4j
@Service
public class DbSaveHandler implements IOrderModelHandler {

    @Override
    public void handleOrderModel(OrderModel orderModel) {
       log.info("保存到数据库");
    }

    @Override
    public boolean supports(OrderModel orderModel) {
        return true;
    }
}
