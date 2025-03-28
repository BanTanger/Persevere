package com.bantanger.design.mediator;

/**
 * 派对成员行为动作封装接口
 * 定义了成员与中介者的交互方式。
 * @author chensongmin
 * @created 2025/3/28
 */

public interface PartyMember {

    /**
     * 加入派对
     * @param party 中介者（派对）
     */
    void joinedParty(Party party);

    /**
     * 响应派对中其他成员的行为
     * @param action 其他成员的行为
     */
    void partyAction(Action action);

    /**
     * 发起行为
     * @param action 当前成员的行为
     */
    void act(Action action);

}
