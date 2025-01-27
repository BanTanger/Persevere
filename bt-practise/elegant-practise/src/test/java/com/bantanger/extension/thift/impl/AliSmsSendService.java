package com.bantanger.extension.thift.impl;

import com.bantanger.extension.executor.Extension;
import com.bantanger.extension.SmsBizId;
import lombok.extern.slf4j.Slf4j;
import com.bantanger.extension.thift.ISmsSendService;
import com.bantanger.extension.thift.SmsSendModel;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/27
 */
@Slf4j
@Extension(bizId = SmsBizId.ALIYUN_SMS)
public class AliSmsSendService implements ISmsSendService {

    @Override
    public boolean sendSms(SmsSendModel sendModel) {
        log.info("【阿里云】短信服务: 发送短信");
        return true;
    }

}
