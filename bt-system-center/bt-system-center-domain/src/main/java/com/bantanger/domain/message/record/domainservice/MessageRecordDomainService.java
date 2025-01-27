package com.bantanger.domain.message.record.domainservice;

import com.bantanger.common.enumtype.MessageErrorType;
import com.bantanger.common.exception.BusinessException;
import com.bantanger.domain.message.record.MessageRecord;
import com.bantanger.domain.message.record.domainservice.model.MessageSendModel;
import com.bantanger.domain.message.record.mapper.MessageRecordMapper;
import com.bantanger.domain.message.record.repository.MessageRecordRepository;
import com.bantanger.domain.message.template.MessageTemplate;
import com.bantanger.domain.message.template.repository.MessageTemplateRepository;
import com.bantanger.jpa.support.EntityOperations;
import jodd.util.StringTemplateParser;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/27
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class MessageRecordDomainService implements IMessageRecordDomainService {

    private final MessageTemplateRepository messageTemplateRepository;
    private final MessageRecordRepository messageRecordRepository;

    @Override
    public void sendMessage(MessageSendModel messageSendModel) {
        MessageTemplate messageTemplate = messageTemplateRepository.findByTemplateCode(
                messageSendModel.getTemplateCode())
            .orElseThrow(() -> new BusinessException(MessageErrorType.TEMPLATE_NOT_FOUND));

        // jodd 字符串模板解析器，性能比 replace 高几倍
        String msgContent = StringTemplateParser
            .ofMap(messageSendModel.getParams())
            .apply(messageTemplate.getTemplate());

        EntityOperations.doCreate(messageRecordRepository)
            .create(() -> MessageRecordMapper.INSTANCE.model2Entity(messageSendModel))
            .update(e -> e.init(msgContent))
            .execute();
    }
}
