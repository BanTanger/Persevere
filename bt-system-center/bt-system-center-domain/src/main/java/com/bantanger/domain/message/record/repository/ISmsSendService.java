package com.bantanger.domain.message.record.repository;

import com.bantanger.domain.message.record.domainservice.model.SmsSendModel;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/27
 */
public interface ISmsSendService {

    boolean sendSms(SmsSendModel sendModel);

}
