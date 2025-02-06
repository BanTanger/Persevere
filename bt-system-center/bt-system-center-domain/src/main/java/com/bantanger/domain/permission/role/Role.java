package com.bantanger.domain.permission.role;

/**
 * @author chensongmin
 * @description 角色模型
 * @date 2025/2/6
 */
import com.bantanger.codegen.processor.api.GenCreateRequest;
import com.bantanger.codegen.processor.api.GenQueryRequest;
import com.bantanger.codegen.processor.api.GenResponse;
import com.bantanger.codegen.processor.api.GenUpdateRequest;
import com.bantanger.codegen.processor.controller.GenController;
import com.bantanger.codegen.processor.creator.GenCreator;
import com.bantanger.codegen.processor.mapper.GenMapper;
import com.bantanger.codegen.processor.query.GenQuery;
import com.bantanger.codegen.processor.query.QueryItem;
import com.bantanger.codegen.processor.repository.GenRepository;
import com.bantanger.codegen.processor.service.GenService;
import com.bantanger.codegen.processor.creator.IgnoreCreator;
import com.bantanger.codegen.processor.updater.IgnoreUpdater;
import com.bantanger.codegen.processor.service.GenServiceImpl;
import com.bantanger.codegen.processor.updater.GenUpdater;
import com.bantanger.codegen.processor.vo.GenVo;
import com.bantanger.common.annotation.FieldDesc;
import com.bantanger.common.constants.GenSourceConstants;
import com.bantanger.common.enums.ValidStatus;
import com.bantanger.jpa.converter.ValidStatusConverter;
import com.bantanger.jpa.support.BaseJpaAggregate;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@GenVo(pkgName = "com.bantanger.domain.permission.role.vo")
@GenCreator(pkgName = "com.bantanger.domain.permission.role.creator")
@GenUpdater(pkgName = "com.bantanger.domain.permission.role.updater")
@GenRepository(pkgName = "com.bantanger.domain.permission.role.repository")
@GenService(pkgName = "com.bantanger.domain.permission.role.service")
@GenServiceImpl(pkgName = "com.bantanger.domain.permission.role.service")
@GenQuery(pkgName = "com.bantanger.domain.permission.role.query")
@GenMapper(pkgName = "com.bantanger.domain.permission.role.mapper")
@GenController(pkgName = "com.bantanger.trigger.http.permission", sourcePath = GenSourceConstants.GEN_CONTROLLER_SOURCE)
@GenCreateRequest(pkgName = "com.bantanger.api.permission.role.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenUpdateRequest(pkgName = "com.bantanger.api.permission.role.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenQueryRequest(pkgName = "com.bantanger.api.permission.role.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenResponse(pkgName = "com.bantanger.api.permission.role.response", sourcePath = GenSourceConstants.GEN_API_SOURCE)
//@GenFeign(pkgName = "com.bantanger.api.permission.api.service",sourcePath = GenSourceConstants.GEN_API_SOURCE,serverName =)
@Entity
@Table(name = "role")
@Data
public class Role extends BaseJpaAggregate {

    @FieldDesc(name = "角色编码")
    private String roleNo;

    @QueryItem
    @FieldDesc(name = "角色名称")
    private String name;

    @QueryItem
    @FieldDesc(name = "平台ID")
    private Long platformId;

    @FieldDesc(name = "备注")
    private String remark;

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
