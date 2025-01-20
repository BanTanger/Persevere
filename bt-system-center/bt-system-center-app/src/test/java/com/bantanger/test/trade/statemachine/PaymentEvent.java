package com.bantanger.test.trade.statemachine;

import lombok.Getter;

/**
 * @author chensongmin
 * @description 支付事件
 * @date 2025/1/20
 */
@Getter
public enum PaymentEvent implements BaseEvent {

    PAY_CREATE("PAY_CREATE", "支付创建事件"),
    PAY_PROCESS("PAY_PROCESS", "支付中"),
    PAY_SUCCESS("PAY_SUCCESS", "支付成功"),
    PAY_FAIL("PAY_FAIL", "支付失败"),

    ;

    private final String event;
    private final String desc;

    PaymentEvent(String event, String desc) {
        this.event = event;
        this.desc = desc;
    }
}
