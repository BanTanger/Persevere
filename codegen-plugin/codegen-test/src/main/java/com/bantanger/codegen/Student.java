package com.bantanger.codegen;

import com.bantanger.codegen.processor.dto.GenDto;
import com.bantanger.codegen.processor.dto.IgnoreDto;
import com.bantanger.commons.annotation.FieldDesc;
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

    @FieldDesc(name = "用户名称")
    private String name;
    @FieldDesc(name = "用户年龄")
    private Integer age;
    @FieldDesc(name = "用户地址")
    private String address;
    @FieldDesc(name = "用户电话")
    private String phone;
    @FieldDesc(name = "用户邮箱")
    private String email;
    @IgnoreDto
    private Integer userStatus;
}
