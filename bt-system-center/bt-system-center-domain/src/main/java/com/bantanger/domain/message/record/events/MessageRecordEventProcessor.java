package com.bantanger.domain.message.record.events;

import com.alibaba.fastjson.JSONObject;
import com.bantanger.domain.message.record.domainservice.model.SmsSendModel;
import com.bantanger.domain.message.record.enums.MsgType;
import com.bantanger.domain.message.record.enums.NotifyType;
import com.bantanger.domain.message.record.events.MessageRecordEvent.MessageRecordCreateEvent;
import com.bantanger.domain.message.record.repository.ISmsSendService;
import com.bantanger.extension.executor.ServiceExecutor;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/27
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class MessageRecordEventProcessor {

    private final ServiceExecutor serviceExecutor;

    /**
     * [验证码
     * ] 处理消息创建事件
     * @param createEvent
     */
    @EventListener
    public void handleMessageRecordForSaveVerifyRecord(
        MessageRecordEvent.MessageRecordCreateEvent createEvent) {
        MsgType msgType = createEvent.messageRecord().getMsgType();
        if (!Objects.equals(msgType, MsgType.VERIFY)) {
            return;
        }
        // todo 保存审核记录

    }

    /**
     * [短信通知扩展点] 处理消息创建事件
     * @param createEvent
     */
    @EventListener
    public void handleMessageForSms(MessageRecordEvent.MessageRecordCreateEvent createEvent) {
        NotifyType notifyType = createEvent.messageRecord().getNotifyType();
        MsgType msgType = createEvent.messageRecord().getMsgType();
        if (Objects.equals(notifyType, NotifyType.SMS)
            && Objects.equals(msgType, MsgType.NOTIFY)) {
            List<String> phones = JSONObject.parseArray(
                createEvent.messageRecord().getExecuteParams(), String.class);

            SmsSendModel smsSendModel = new SmsSendModel();
            smsSendModel.setPhones(phones);
            // 发送短信通知
            Boolean sendResult = serviceExecutor.execute(ISmsSendService.class, SmsBiz.ALIYUN_SMS,
                service -> {
                    return service.sendSms(smsSendModel);
                });
        }
    }

    /**
     * [邮件通知] 处理消息创建事件
     * @param createEvent
     */
    @EventListener
    public void handleMessageForEmail(MessageRecordCreateEvent createEvent){

    }


}
