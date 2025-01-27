package com.bantanger.extension;

import com.bantanger.extension.executor.BizEnum;
import lombok.Getter;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/27
 */
@Getter
public enum SmsBiz implements BizEnum {

    ALIYUN_SMS(SmsBizId.ALIYUN_SMS, "阿里云短信服务"),
    TENCENT_SMS(SmsBizId.TENCENT_SMS, "腾讯云短信服务"),
    RONGYUN_SMS(SmsBizId.RONGYUN_SMS, "融云短信服务"),
    TIAN_YI_YUN_SMS(SmsBizId.TIAN_YI_YUN_SMS, "天翼云短信服务"),

    ;

    private final String bizId;
    private final String desc;

    SmsBiz(String bizId, String desc) {
        this.bizId = bizId;
        this.desc = desc;
    }

}
