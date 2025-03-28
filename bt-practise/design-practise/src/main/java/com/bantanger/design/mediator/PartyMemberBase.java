package com.bantanger.design.mediator;

import lombok.extern.slf4j.Slf4j;

/**
 * @author chensongmin
 * @created 2025/3/28
 */
@Slf4j
public abstract class PartyMemberBase implements PartyMember {

    // 中介者角色
    protected Party party;

    @Override
    public void joinedParty(Party party) {
        log.info("{} 加入了派对", this.getClass().getSimpleName());
        this.party = party;
    }

    @Override
    public void partyAction(Action action) {
        log.info("{} 执行 {} 动作", this, action.getDescription());
    }

    @Override
    public void act(Action action) {
        if (party != null) {
            log.info("{} {}", this, action);
            party.act(this, action);
        }
    }

    public abstract String toString();
}

