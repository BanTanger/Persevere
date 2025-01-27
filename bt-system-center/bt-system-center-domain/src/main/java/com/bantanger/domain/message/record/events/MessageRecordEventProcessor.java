package com.bantanger.domain.message.record.events;

import com.bantanger.domain.message.record.enums.MsgType;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/27
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class MessageRecordEventProcessor {

    @EventListener
    public void handleMessageRecordForSaveVerifyRecord(MessageRecordEvent.MessageRecordCreateEvent createEvent) {
        MsgType msgType = createEvent.messageRecord().getMsgType();
        if (!Objects.equals(msgType, MsgType.VERIFY)) {
            return ;
        }
        // 保存审核记录

    }

}
