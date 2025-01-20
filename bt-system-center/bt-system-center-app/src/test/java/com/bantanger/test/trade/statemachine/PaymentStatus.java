package com.bantanger.test.trade.statemachine;

import lombok.Getter;

/**
 * @author chensongmin
 * @description 支付状态
 * @date 2025/1/20
 */
@Getter
public enum PaymentStatus implements BaseStatus {

    INIT("INIT", "初始化"),
    PAYING("PAYING", "支付中"),
    PAID("PAID", "支付成功"),
    FAILED("FAILED", "支付失败"),

    ;

    private static final StatusMachine<PaymentStatus, PaymentEvent> PAY_STATE_MACHINE = new StatusMachine<>();

    /*
     * 装配支付状态机
     */
    static {
        // 初始状态
        PAY_STATE_MACHINE.accept(null, PaymentEvent.PAY_CREATE, INIT);
        // 支付中
        PAY_STATE_MACHINE.accept(INIT, PaymentEvent.PAY_PROCESS, PAYING);
        // 支付成功
        PAY_STATE_MACHINE.accept(PAYING, PaymentEvent.PAY_SUCCESS, PAID);
        // 支付失败
        PAY_STATE_MACHINE.accept(PAYING, PaymentEvent.PAY_FAIL, FAILED);
    }

    private final String status;
    private final String desc;

    PaymentStatus(String status, String desc) {
        this.status = status;
        this.desc = desc;
    }

    public static PaymentStatus getTargetStatus(PaymentStatus status, PaymentEvent event) {
        return PAY_STATE_MACHINE.getTargetStatus(status, event);
    }
}
