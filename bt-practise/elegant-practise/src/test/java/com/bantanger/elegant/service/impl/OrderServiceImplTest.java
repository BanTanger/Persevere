package com.bantanger.elegant.service.impl;

import static org.junit.jupiter.api.Assertions.*;

import com.bantanger.elegant.order.BizLineEnum;
import com.bantanger.elegant.order.OrderRequest;
import com.bantanger.elegant.service.IOrderService;
import com.bantanger.elegant.user.User;
import com.bantanger.elegant.user.UserCreditType;
import com.bantanger.elegant.user.UserLevelType;
import com.bantanger.elegant.user.repository.UserRepository;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/7
 */
@SpringBootTest
class OrderServiceImplTest {

    @Resource
    private UserRepository userRepository;
    @Resource
    private IOrderService orderService;

    @BeforeEach
    void setUp() {
        User user = User.builder()
            .username("test")
            .address("test")
            .userLevelType(UserLevelType.USER_VIP_1)
            .userCreditType(UserCreditType.CREDIT_NORMAL)
            .build();

        userRepository.save(user);
    }

    @Test
    void handleOrder() {
        OrderRequest orderRequest = OrderRequest.builder()
            .userId(1L)
            .businessId("1")
            .productId("1")
            .bizCode(BizLineEnum.YW1)
            .build();
        orderService.handleOrder(orderRequest);
    }
}