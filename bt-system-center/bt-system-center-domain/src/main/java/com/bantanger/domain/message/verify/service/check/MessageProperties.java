package com.bantanger.domain.message.verify.service.check;

import com.bantanger.common.annotation.FieldDesc;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/28
 */
@Component
@ConfigurationProperties(prefix = "bt.message")
@Setter
@Getter
public class MessageProperties {

    @FieldDesc(name = "验证码长度")
    private Integer verifyLength;

    @FieldDesc(name = "有效的时长(分钟)")
    private Integer validTime;

    @FieldDesc(name = "发送间隔(分钟)")
    private Integer sendInterval;

    @FieldDesc(name = "某类短信当天发送最多次数(根据模板判断)")
    private Integer sendMaxTimes;
}
