package com.bantanger.elegant.service.impl;

import com.bantanger.elegant.order.OrderModel;
import com.bantanger.elegant.service.IOrderModelHandler;
import com.bantanger.elegant.user.UserLevelType;
import com.bantanger.elegant.user.UserModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/6
 */
@Slf4j
@Service
public class UserVipHandler implements IOrderModelHandler {

    @Override
    public void handleOrderModel(OrderModel orderModel) {
        log.info("用户VIP处理");
        UserModel userModel = orderModel.getUserModel();
        UserLevelType userLevelType = userModel.getUserLevelType();
        if (userLevelType == UserLevelType.USER_VIP_1) {
            log.info("用户VIP1处理, 优惠10元");
        } else if (userLevelType == UserLevelType.USER_VIP_2) {
            log.info("用户VIP2处理, 优惠20元");
        } else if (userLevelType == UserLevelType.USER_VIP_3) {
            log.info("用户VIP3处理, 优惠30");
        }
    }

    @Override
    public boolean supports(OrderModel orderModel) {
        return orderModel.getUserModel().getUserLevelType() != null;
    }

}
