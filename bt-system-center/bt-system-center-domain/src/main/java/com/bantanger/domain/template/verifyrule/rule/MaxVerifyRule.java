package com.bantanger.domain.template.verifyrule.rule;

import com.bantanger.common.verifyrule.VerifyTypeEnum;
import java.math.BigDecimal;
import java.util.Optional;
import lombok.Getter;
import lombok.Setter;

/**
 * @author chensongmin
 * @description
 * @date 2025/2/7
 */
@Setter
@Getter
public class MaxVerifyRule extends AbstractVerifyRule {

    private BigDecimal maxValue;

    protected MaxVerifyRule() {
        super(VerifyTypeEnum.MAX_VALUE);
    }

    /**
     * @param input
     * @return
     */
    @Override
    public Optional<String> verify(String input) {
//        boolean db = GenericValidator.isDouble(input);
//        if (db) {
//            if (NumberUtil.isGreater(new BigDecimal(input), maxValue)) {
//                return Optional.of(TemplateErrorType.INPUT_TOO_BIG.getName());
//            }
//        } else {
//            return Optional.of(TemplateErrorType.FORMAT_ERROR.getName());
//        }
        return Optional.empty();
    }
}
