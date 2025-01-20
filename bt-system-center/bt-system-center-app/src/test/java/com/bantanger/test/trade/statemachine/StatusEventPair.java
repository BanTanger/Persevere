package com.bantanger.test.trade.statemachine;

import java.util.Objects;

/**
 * @author chensongmin
 * @description 状态事件对，指定的状态只能接收指定的事件
 * @date 2025/1/20
 */
public class StatusEventPair<S extends BaseStatus, E extends BaseEvent> {

    private final S status;
    private final E event;

    public StatusEventPair(S status, E event) {
        this.status = status;
        this.event = event;
    }

    @Override
    public boolean equals(Object o) {
        if (o instanceof StatusEventPair<?,?> obj) {
            return this.status.equals(obj.status) && this.event.equals(obj.event);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(status, event);
    }
}
