package com.bantanger.test.trade.statemachine;

import java.util.Objects;

/**
 * @author chensongmin
 * @description 支付单类型
 * @date 2025/1/20
 */
public class PaymentModel {

    /**
     * 上一次状态
     */
    private PaymentStatus prevStatus;
    /**
     * 当前状态
     */
    private PaymentStatus currStatus;
    
    public void transferStatus(PaymentEvent event) {
        // 获取目标状态
        PaymentStatus targetStatus = PaymentStatus.getTargetStatus(currStatus, event);
        // 如果目标状态不为空，当前状态推进
        if (Objects.nonNull(targetStatus)) {
            prevStatus = currStatus;
            currStatus = targetStatus;
        } else {
            // 目标状态为空，说明非法推进，进入异常处理
//            throw new StateMachineException(currStatus, event, "状态转换失败");
            throw new RuntimeException("状态转换失败, 当前状态:" + currStatus + " 事件:" + event);
        }
    }
}
