package com.bantanger.domain.message.record.events;

import cn.hutool.core.map.MapUtil;
import cn.hutool.core.util.RandomUtil;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson2.JSON;
import com.bantanger.common.constants.MessageConstants;
import com.bantanger.common.errortype.MessageErrorType;
import com.bantanger.common.exception.BusinessException;
import com.bantanger.domain.message.record.MessageRecord;
import com.bantanger.domain.message.record.domainservice.model.SmsSendModel;
import com.bantanger.domain.message.record.enums.MsgType;
import com.bantanger.domain.message.record.enums.NotifyType;
import com.bantanger.domain.message.record.events.MessageRecordEvent.MessageRecordCreateEvent;
import com.bantanger.domain.message.record.repository.ISmsSendService;
import com.bantanger.domain.message.verify.creator.VerifyRecordCreator;
import com.bantanger.domain.message.verify.service.IVerifyRecordService;
import com.bantanger.domain.message.verify.service.check.CheckContext;
import com.bantanger.domain.message.verify.service.check.MessageProperties;
import com.bantanger.domain.message.verify.service.check.SendIntervalChecker;
import com.bantanger.domain.message.verify.service.check.SendMaxTimesChecker;
import com.bantanger.extension.executor.ServiceExecutor;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.bytebuddy.matcher.ElementMatchers;
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

    private final MessageProperties messageProperties;
    private final ServiceExecutor serviceExecutor;
    private final SendIntervalChecker sendIntervalChecker;
    private final SendMaxTimesChecker sendMaxTimesChecker;
    private final IVerifyRecordService verifyRecordService;

    /**
     * [验证码] 处理消息创建事件
     *
     * @param createEvent
     */
    @EventListener
    public void handleMessageForVerifyRecord(
        MessageRecordEvent.MessageRecordCreateEvent createEvent)
    {
        MessageRecord messageRecord = createEvent.messageRecord();
        MsgType msgType = messageRecord.getMsgType();
        if (Objects.equals(msgType, MsgType.VERIFY)) {
            // 如果是验证码，存储验证码记录
            var checkContext = msgRecordCreateEvent2CheckContext(createEvent);
            // 验证策略: 【是否达到发送最大次数？发送频率是否过快？】
            // 属于是否问题，可以使用 ElementMatch 封装
            boolean checkResult = ElementMatchers.any()
                .and(sendIntervalChecker)
                .and(sendMaxTimesChecker)
                .matches(checkContext);
            if (!checkResult) {
                log.error("发送过于频繁! checkContext: {}", JSON.toJSONString(checkContext));
                throw new BusinessException(MessageErrorType.MESSAGE_SEND_FAST);
            }

            String genVerifyCode = RandomUtil.randomNumbers(messageProperties.getVerifyLength());
            long endTime = Instant.now()
                .plus(messageProperties.getSendInterval(), ChronoUnit.MINUTES).toEpochMilli();
            messageRecord.setMessageContent(messageRecord.getMessageContent().concat(genVerifyCode));

            log.info("生成验证码:{}, 发送消息内容为:{}", genVerifyCode, messageRecord.getMessageContent());
            VerifyRecordCreator creator = buildVerifyRecordCreator(
                messageRecord, checkContext, genVerifyCode, endTime);
            // 持久化验证码记录 todo 对性能有影响可以通过 mq 异步落盘
            verifyRecordService.createVerifyRecord(creator);
        }
    }

    /**
     * [短信通知扩展点] 处理消息创建事件
     *
     * @param createEvent
     */
    @EventListener
    public void handleMessageForSms(MessageRecordEvent.MessageRecordCreateEvent createEvent) {
        MessageRecord messageRecord = createEvent.messageRecord();
        NotifyType notifyType = messageRecord.getNotifyType();
        MsgType msgType = messageRecord.getMsgType();

        if (Objects.equals(notifyType, NotifyType.SMS)
            && Objects.equals(msgType, MsgType.NOTIFY)) {
            List<String> phones = JSONObject.parseArray(messageRecord.getParams(), String.class);

            SmsSendModel smsSendModel = new SmsSendModel();
            smsSendModel.setPhones(phones);
            // 发送短信通知
            Boolean sendResult = serviceExecutor.execute(ISmsSendService.class, SmsBiz.ALIYUN_SMS,
                service -> {
                    log.info("发送消息内容为:{}", messageRecord.getMessageContent());
                    return service.sendSms(smsSendModel);
                });
        }
    }

    /**
     * [邮件通知] 处理消息创建事件
     *
     * @param createEvent
     */
    @EventListener
    public void handleMessageForEmail(MessageRecordCreateEvent createEvent) {

    }

    private static VerifyRecordCreator buildVerifyRecordCreator(
        MessageRecord messageRecord, CheckContext checkContext, String genVerifyCode, long endTime)
    {
        VerifyRecordCreator creator = new VerifyRecordCreator();
        creator.setAccount(checkContext.getAccount());
        creator.setTemplateCode(checkContext.getTemplateCode());
        creator.setContent(messageRecord.getMessageContent());
        creator.setVerifyCode(genVerifyCode);
        creator.setEndTime(endTime);
        return creator;
    }

    private static CheckContext msgRecordCreateEvent2CheckContext(
        MessageRecordCreateEvent createEvent)
    {
        Map<String, Object> params = JSONObject
            .parseObject(createEvent.messageRecord().getParams());
        CheckContext checkContext = new CheckContext();
        checkContext.setAccount(MapUtil.getStr(params, MessageConstants.ACCOUNT));
        checkContext.setTemplateCode(createEvent.messageRecord().getTemplateCode());
        return checkContext;
    }

}
