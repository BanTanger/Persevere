package com.bantanger.extension;

import com.bantanger.ElegantApplication;
import com.bantanger.extension.executor.ServiceExecutor;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import com.bantanger.extension.thift.ISmsSendService;
import com.bantanger.extension.thift.SmsSendModel;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/27
 */
@Slf4j
@ComponentScan(basePackages = {"com.bantanger.extension"})
@SpringBootTest(classes = ElegantApplication.class)
public class SmsSendTest {

    @Resource
    private ServiceExecutor serviceExecutor;

    @Test
    public void handleMessageForSms() {
        Boolean sendResult = serviceExecutor.execute(ISmsSendService.class, SmsBiz.ALIYUN_SMS,
            smsSendService -> {
                return smsSendService.sendSms(new SmsSendModel());
            });
        log.info("短信发送结果: {}", sendResult);
    }
}
