package com.bantanger.lambda;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/1
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {

    private String name;
    private Integer age;
    private Sex gender;

    enum Sex {
        MALE,
        FEMALE,
    }

}
