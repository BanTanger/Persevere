package com.bantanger.domain.user;

/**
 * @author chensongmin
 * @description 用户账号
 * @date 2025/2/6
 */

import com.bantanger.codegen.processor.api.GenCreateRequest;
import com.bantanger.codegen.processor.api.GenQueryRequest;
import com.bantanger.codegen.processor.api.GenResponse;
import com.bantanger.codegen.processor.api.GenUpdateRequest;
import com.bantanger.codegen.processor.controller.GenController;
import com.bantanger.codegen.processor.creator.GenCreator;
import com.bantanger.codegen.processor.creator.IgnoreCreator;
import com.bantanger.codegen.processor.mapper.GenMapper;
import com.bantanger.codegen.processor.query.GenQuery;
import com.bantanger.codegen.processor.repository.GenRepository;
import com.bantanger.codegen.processor.service.GenService;
import com.bantanger.codegen.processor.service.GenServiceImpl;
import com.bantanger.codegen.processor.updater.GenUpdater;
import com.bantanger.codegen.processor.updater.IgnoreUpdater;
import com.bantanger.codegen.processor.vo.GenVo;
import com.bantanger.codegen.processor.vo.IgnoreVo;
import com.bantanger.common.annotation.FieldDesc;
import com.bantanger.common.annotation.TypeConverter;
import com.bantanger.common.constants.GenSourceConstants;
import com.bantanger.common.enums.ValidStatus;
import com.bantanger.jpa.converter.ValidStatusConverter;
import com.bantanger.jpa.support.BaseJpaAggregate;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@GenVo(pkgName = "com.bantanger.domain.user.vo")
@GenCreator(pkgName = "com.bantanger.domain.user.creator")
@GenUpdater(pkgName = "com.bantanger.domain.user.updater")
@GenRepository(pkgName = "com.bantanger.domain.user.repository")
@GenService(pkgName = "com.bantanger.domain.user.service")
@GenServiceImpl(pkgName = "com.bantanger.domain.user.service")
@GenQuery(pkgName = "com.bantanger.domain.user.query")
@GenMapper(pkgName = "com.bantanger.domain.user.mapper")
@GenController(pkgName = "com.bantanger.trigger.http.user", sourcePath = GenSourceConstants.GEN_CONTROLLER_SOURCE)
@GenCreateRequest(pkgName = "com.bantanger.api.user.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenUpdateRequest(pkgName = "com.bantanger.api.user.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenQueryRequest(pkgName = "com.bantanger.api.user.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenResponse(pkgName = "com.bantanger.api.user.response", sourcePath = GenSourceConstants.GEN_API_SOURCE)
//@GenFeign(pkgName = "com.bantanger.api.user.api.service",sourcePath = GenSourceConstants.GEN_API_SOURCE,serverName =)
@Entity
@Table(name = "user_account")
@Data
public class UserAccount extends BaseJpaAggregate {

    @FieldDesc(name = "用户ID")
    private Long userId;

    @FieldDesc(name = "账号ID")
    private String accountNo;

    @FieldDesc(name = "密码")
    @IgnoreVo
    private String password;

    @FieldDesc(name = "账号类别")
    @Convert(converter = AccountTypeConverter.class)
    @TypeConverter
    private AccountType accountType;

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
