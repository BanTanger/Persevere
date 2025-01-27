package com.bantanger.domain.message.record.domainservice.model;

import com.bantanger.common.annotation.FieldDesc;
import com.bantanger.domain.message.record.enums.MsgType;
import com.bantanger.domain.message.record.enums.NotifyType;
import java.util.Map;
import lombok.Getter;
import lombok.Setter;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/27
 */
@Setter
@Getter
public class MessageSendModel {

    @FieldDesc(name = "通知类型")
    private NotifyType notifyType;

    @FieldDesc(name = "模板编码")
    private String templateCode;

    @FieldDesc(name = "发送参数")
    private Map<String, Object> params;

    @FieldDesc(name = "消息类型")
    private MsgType msgType;
}
