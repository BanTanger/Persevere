package com.bantanger.elegant.filters;

import com.bantanger.elegant.order.OrderContext;
import com.bantanger.elegant.user.UserModel;
import com.bantanger.elegant.pipeline.AbstractEventFilter;
import com.bantanger.elegant.service.facade.IOrderPipelineFacadeService;
import com.bantanger.elegant.service.facade.IUserFacadeService;
import com.bantanger.elegant.user.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Optional;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/5
 */
@Slf4j
@RequiredArgsConstructor
public class UserValidFilter extends AbstractEventFilter<OrderContext> {

    // 使用门面统一封装 repo 接口，保证 filter 的简洁性和封装性
    private final IOrderPipelineFacadeService orderPipelineFacadeService;

    @Override
    protected void handle(OrderContext context) {
        IUserFacadeService userFacadeService = orderPipelineFacadeService.getUserFacadeService();
        Optional<User> user = userFacadeService.findUserInfoById(context.getOrderRequest().getUserId());
        user.ifPresent(s -> {
            log.info("业务={}, 鉴权用户 userId={} 合法性，结果：用户存在", context.getBizCode(), s.getId());
            // 存入用户编程模型上下文
            context.setUserModel(buildUserModel(s));
        });
    }

    private UserModel buildUserModel(User user) {
        return UserModel.builder()
                .userId(user.getId())
                .username(user.getUsername())
                .address(user.getAddress())
                .userLevelType(user.getUserLevelType())
                .userCreditType(user.getUserCreditType())
                .build();
    }
}
