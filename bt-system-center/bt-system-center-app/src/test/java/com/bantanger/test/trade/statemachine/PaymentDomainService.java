package com.bantanger.test.trade.statemachine;

/**
 * @author chensongmin
 * @description 支付领域服务
 * @date 2025/1/20
 */
interface IPaymentDomainService {
    void notify(PaymentNotifyMessage message);
}

public class PaymentDomainService implements IPaymentDomainService {

    @Override
    public void notify(PaymentNotifyMessage message) {
        // 查本地缓存获得 paymentModel
//        PaymentModel paymentModel = loadPaymentModel(message.getPaymentId());
        // 简化直接 new
        PaymentModel paymentModel = new PaymentModel();
        try {
            // 状态推进
            paymentModel.transferStatus(message.paymentEvent());
            // savePaymentModel (db)...
            // save log ...
            // send mq ...
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

record PaymentNotifyMessage(String paymentId, PaymentEvent paymentEvent) {
}
