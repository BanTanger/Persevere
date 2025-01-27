package com.bantanger.domain.message.record.domainservice;

import com.bantanger.domain.message.record.domainservice.model.MessageSendModel;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/27
 */
public interface IMessageRecordDomainService {

    /**
     * 发送消息
     * @param messageSendModel
     */
    void sendMessage(MessageSendModel messageSendModel);

}
