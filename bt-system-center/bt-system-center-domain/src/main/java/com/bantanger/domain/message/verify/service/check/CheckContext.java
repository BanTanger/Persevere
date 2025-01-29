package com.bantanger.domain.message.verify.service.check;

import com.bantanger.common.annotation.FieldDesc;
import lombok.Getter;
import lombok.Setter;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/28
 */
@Setter
@Getter
public class CheckContext {

    @FieldDesc(name = "目的账号")
    private String account;

    @FieldDesc(name = "发送间隔")
    private Integer sendInterval;

    @FieldDesc(name = "发送模板")
    private String templateCode;

    @FieldDesc(name = "发送最大次数")
    private Integer sendMaxTimes;

}
