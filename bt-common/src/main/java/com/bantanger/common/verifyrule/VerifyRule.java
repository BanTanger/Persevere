package com.bantanger.common.verifyrule;

import java.util.Optional;

/**
 * @author chensongmin
 * @description
 * @date 2025/2/7
 */
public interface VerifyRule {

    Optional<String> verify(String input);

    VerifyTypeEnum getVerifyType();

}
