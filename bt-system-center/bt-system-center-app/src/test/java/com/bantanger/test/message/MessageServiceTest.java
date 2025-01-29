package com.bantanger.test.message;

import com.bantanger.common.constants.MessageConstants;
import com.bantanger.domain.message.record.domainservice.IMessageRecordDomainService;
import com.bantanger.domain.message.record.domainservice.model.MessageSendModel;
import com.bantanger.domain.message.record.enums.MsgType;
import com.bantanger.domain.message.record.enums.NotifyType;
import com.bantanger.domain.message.template.creator.MessageTemplateCreator;
import com.bantanger.domain.message.template.enums.TemplateType;
import com.bantanger.domain.message.template.service.IMessageTemplateService;
import jakarta.annotation.Resource;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/28
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class MessageServiceTest {

    private static final String SMS_REGISTER = "sms_register";
    private static final String SMS_REGISTER2 = "sms_register2";

    @Resource
    private IMessageRecordDomainService messageRecordDomainService;

    @Resource
    private IMessageTemplateService messageTemplateService;

    @Test
    public void testAddTemplate() {
        MessageTemplateCreator creator = new MessageTemplateCreator();
        creator.setTemplate("发送短信，验证码为:${verifyCode}");
        creator.setTemplateCode(SMS_REGISTER);
        creator.setName("短信注册");
        creator.setTemplateType(TemplateType.SMS);
        creator.setDescription("短信注册模板");
        messageTemplateService.createMessageTemplate(creator);
    }

    @Test
    public void testVerifySmsSend() {
        MessageSendModel messageSendModel = new MessageSendModel();
        messageSendModel.setNotifyType(NotifyType.SMS);
        messageSendModel.setMsgType(MsgType.VERIFY);
        messageSendModel.setTemplateCode(SMS_REGISTER);
        messageSendModel.setParams(Map.of(MessageConstants.ACCOUNT, "13888888888"));
        messageRecordDomainService.sendMessage(messageSendModel);
    }

    @Test
    public void testNotifySmsSend() {
        MessageSendModel messageSendModel = new MessageSendModel();
        messageSendModel.setNotifyType(NotifyType.SMS);
        messageSendModel.setMsgType(MsgType.NOTIFY);
        messageSendModel.setTemplateCode(SMS_REGISTER);
        messageSendModel.setParams(Map.of(MessageConstants.ACCOUNT, "13888888888"));
        messageRecordDomainService.sendMessage(messageSendModel);
    }

    @Test
    public void testAddTemplate2() {
        MessageTemplateCreator creator = new MessageTemplateCreator();
        creator.setTemplate("尊敬的用户:${account}, 您的验证码为:${verifyCode}");
        creator.setTemplateCode(SMS_REGISTER2);
        creator.setName("短信注册2");
        creator.setTemplateType(TemplateType.SMS);
        creator.setDescription("短信注册模板2");
        messageTemplateService.createMessageTemplate(creator);
    }

    @Test
    public void testNotifySmsSend2() {
        MessageSendModel messageSendModel = new MessageSendModel();
        messageSendModel.setNotifyType(NotifyType.SMS);
        messageSendModel.setMsgType(MsgType.NOTIFY);
        messageSendModel.setTemplateCode(SMS_REGISTER2);
        messageSendModel.setParams(Map.of(MessageConstants.ACCOUNT, "13888888888"));
        messageRecordDomainService.sendMessage(messageSendModel);
    }
}
