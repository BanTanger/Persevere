package com.bantanger.domain.message.record.events;

import com.bantanger.domain.message.record.MessageRecord;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/27
 */
public interface MessageRecordEvent {

    /**
     * 消息记录创建事件
     * @param messageRecord
     */
    record MessageRecordCreateEvent(MessageRecord messageRecord) {}

}
