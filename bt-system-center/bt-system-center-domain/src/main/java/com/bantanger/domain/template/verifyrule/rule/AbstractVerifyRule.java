package com.bantanger.domain.template.verifyrule.rule;

import com.bantanger.common.verifyrule.VerifyRule;
import com.bantanger.common.verifyrule.VerifyTypeEnum;

/**
 * @author chensongmin
 * @description
 * @date 2025/2/7
 */
public abstract class AbstractVerifyRule implements VerifyRule {

    private final VerifyTypeEnum verifyTypeEnum;

    protected AbstractVerifyRule(VerifyTypeEnum verifyTypeEnum) {
        this.verifyTypeEnum = verifyTypeEnum;
    }

    /**
     * @return
     */
    @Override
    public VerifyTypeEnum getVerifyType() {
        return this.verifyTypeEnum;
    }
}
