package com.bantanger.domain.message.record.domainservice.model;

import com.bantanger.common.annotation.FieldDesc;
import com.bantanger.domain.message.record.enums.MsgType;
import com.bantanger.domain.message.record.enums.NotifyType;
import java.util.List;
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
public class SmsSendModel extends MessageSendModel {

    @FieldDesc(name = "手机号列表")
    private List<String> phones;

    @FieldDesc(name = "消息类型-[通知]")
    private MsgType msgType = MsgType.NOTIFY;

    @FieldDesc(name = "通知类型-[短信]")
    private NotifyType notifyType = NotifyType.SMS;

}
