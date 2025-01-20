package com.bantanger.test.trade.statemachine;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chensongmin
 * @description 状态机
 * @date 2025/1/20
 */
public class StatusMachine<S extends BaseStatus, E extends BaseEvent> {

    private final Map<StatusEventPair<S, E>, S> statusEventMap = new HashMap<>();

    /**
     * 装配状态机，源状态接收事件后能抵达哪种目标状态
     * @param sourceStatus 源状态
     * @param event 对应事件
     * @param targetStatus 流转的目标状态
     */
    public void accept(S sourceStatus, E event, S targetStatus) {
        statusEventMap.put(new StatusEventPair<>(sourceStatus, event), targetStatus);
    }

    /**
     * 通过源状态和事件类型获取目标状态
     * @param sourceStatus 源状态
     * @param event 对应事件
     * @return targetStatus 流转的目标状态
     */
    public S getTargetStatus(S sourceStatus, E event) {
        return statusEventMap.get(new StatusEventPair<>(sourceStatus, event));
    }

}
