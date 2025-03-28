package com.bantanger.design.mediator;

/**
 * 派对接口（中介者）
 * @author chensongmin
 * @created 2025/3/28
 */

public interface Party {

    /**
     * 添加成员到派对
     * @param member 派对成员
     */
    void addMember(PartyMember member);

    /**
     * 协调派对成员的行为
     * @param member 发起行为的成员
     * @param action 成员执行的行为
     */
    void act(PartyMember member, Action action);
}
