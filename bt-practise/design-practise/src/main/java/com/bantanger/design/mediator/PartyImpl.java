/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */

package com.bantanger.design.mediator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chensongmin02
 * @created 2025/3/28
 */

public class PartyImpl implements Party {

    private final List<PartyMember> members;

    public PartyImpl() {
        members = new ArrayList<>();
    }

    @Override
    public void addMember(PartyMember member) {
        members.add(member);
        member.joinedParty(this);
    }

    /**
     * 通知所有成员执行特定的动作，除了触发该动作的成员本身。
     *
     * @param actor 触发动作的成员
     * @param action 要执行的动作
     */
    @Override
    public void act(PartyMember actor, Action action) {
        // 遍历所有成员，过滤掉触发动作的成员
        members.stream().filter(member -> !member.equals(actor))
                // 对每个剩余的成员执行指定的动作
                .forEach(member -> member.partyAction(action));
    }
}