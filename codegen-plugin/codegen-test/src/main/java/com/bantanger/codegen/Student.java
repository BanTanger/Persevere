package com.bantanger.codegen;

import com.bantanger.codegen.processor.dto.GenDto;
import com.bantanger.codegen.processor.dto.IgnoreDto;
import lombok.Getter;
import lombok.Setter;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/7
 */
@Getter
@Setter
@GenDto(pkgName = "com.bantanger.codegen.dto")
public class Student {

    private String name;
    private Integer age;
    @IgnoreDto
    private String address;
    private String phone;
    private String email;

}
