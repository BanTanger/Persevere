package com.bantanger.infrastructure.mq.config;

/**
 * 消息基类，对 MQ 二次封装，https://www.cnblogs.com/jianzh5/p/17301690.html
 * @author chensongmin
 * @created 2025/3/7
 */

import lombok.Data;

import java.time.LocalDateTime;

@Data
public abstract class BaseMessage {
    /**
     * 业务键，用于RocketMQ控制台查看消费情况
     */
    protected String key;
    /**
     * 发送消息来源，用于排查问题
     */
    protected String source = "";

    /**
     * 发送时间
     */
    protected LocalDateTime sendTime = LocalDateTime.now();

    /**
     * 重试次数，用于判断重试次数，超过重试次数发送异常警告
     */
    protected Integer retryTimes = 0;
}
