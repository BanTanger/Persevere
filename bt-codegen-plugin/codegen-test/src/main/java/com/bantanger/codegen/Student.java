package com.bantanger.codegen;

import com.bantanger.codegen.processor.api.GenCreateRequest;
import com.bantanger.codegen.processor.api.GenQueryRequest;
import com.bantanger.codegen.processor.api.GenResponse;
import com.bantanger.codegen.processor.api.GenUpdateRequest;
import com.bantanger.codegen.processor.controller.GenController;
import com.bantanger.codegen.processor.creator.GenCreator;
import com.bantanger.codegen.processor.creator.IgnoreCreator;
import com.bantanger.codegen.processor.mapper.GenEntityMapper;
import com.bantanger.codegen.processor.mapper.GenMapper;
import com.bantanger.codegen.processor.query.GenQuery;
import com.bantanger.codegen.processor.repository.GenRepository;
import com.bantanger.codegen.processor.service.GenService;
import com.bantanger.codegen.processor.service.GenServiceImpl;
import com.bantanger.codegen.processor.updater.GenUpdater;
import com.bantanger.codegen.processor.updater.IgnoreUpdater;
import com.bantanger.codegen.processor.vo.GenVo;
import com.bantanger.common.annotation.FieldDesc;
import com.bantanger.common.constants.ValidStatus;
import com.bantanger.jpa.converter.ValidStatusConverter;
import com.bantanger.jpa.support.BaseJpaAggregate;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/8
 */
@GenVo(pkgName = "com.bantanger.codegen.vo")
@GenCreator(pkgName = "com.bantanger.codegen.creator")
@GenUpdater(pkgName = "com.bantanger.codegen.updater")
@GenRepository(pkgName = "com.bantanger.codegen.repository")
@GenService(pkgName = "com.bantanger.codegen.service")
@GenServiceImpl(pkgName = "com.bantanger.codegen.service")
@GenQuery(pkgName = "com.bantanger.codegen.query")
@GenEntityMapper(pkgName = "com.bantanger.codegen.mapper")
@GenMapper(pkgName = "com.bantanger.codegen.mapper")
@GenController(pkgName = "com.bantanger.codegen.controller")
@GenCreateRequest(pkgName = "com.bantanger.codegen.request")
@GenUpdateRequest(pkgName = "com.bantanger.codegen.request")
@GenQueryRequest(pkgName = "com.bantanger.codegen.request")
@GenResponse(pkgName = "com.bantanger.codegen.response")
@Entity
@Table(name = "student")
@Data
public class Student extends BaseJpaAggregate {

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

    @Convert(converter = ValidStatusConverter.class)
    @IgnoreUpdater
    @IgnoreCreator
    private ValidStatus validStatus;

    public void init() {
        setValidStatus(ValidStatus.VALID);
    }

    public void valid() {
        setValidStatus(ValidStatus.VALID);
    }

    public void invalid() {
        setValidStatus(ValidStatus.INVALID);
    }
}
