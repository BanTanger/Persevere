package com.bantanger.domain.pay;

import java.math.BigDecimal;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/12
 */
public class AbstractPayItem implements PayItem {

    private BigDecimal money;

    @Override
    public BigDecimal getPayInfo() {
        return money;
    }

}
