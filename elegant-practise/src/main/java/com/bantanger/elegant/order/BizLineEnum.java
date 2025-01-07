package com.bantanger.elegant.order;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/5
 */
@Getter
@AllArgsConstructor
public enum BizLineEnum {
    YW1("YW1"),
    YW2("YW2"),
    ;

    private final String code;

}
