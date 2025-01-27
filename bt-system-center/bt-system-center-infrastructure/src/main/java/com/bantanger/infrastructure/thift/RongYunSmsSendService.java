package com.bantanger.infrastructure.thift;

import com.bantanger.domain.message.record.domainservice.model.SmsSendModel;
import com.bantanger.domain.message.record.events.SmsBizId;
import com.bantanger.domain.message.record.repository.ISmsSendService;
import com.bantanger.extension.executor.Extension;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/27
 */
@Extension(bizId = SmsBizId.RONGYUN_SMS)
public class RongYunSmsSendService implements ISmsSendService {

    @Override
    public boolean sendSms(SmsSendModel sendModel) {
        return false;
    }

}
