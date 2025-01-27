package com.bantanger.infrastructure.thift;

import com.bantanger.domain.message.record.events.SmsBizId;
import com.bantanger.domain.message.record.repository.ISmsSendService;
import com.bantanger.domain.message.record.domainservice.model.SmsSendModel;
import com.bantanger.extension.executor.Extension;
import lombok.extern.slf4j.Slf4j;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/27
 */
@Slf4j
@Extension(bizId = SmsBizId.TENCENT_SMS)
public class TencentSmsSendService implements ISmsSendService {

    @Override
    public boolean sendSms(SmsSendModel sendModel) {
        log.info("【腾讯云】短信服务: 发送短信");
        return true;
    }

}
