package com.bantanger.domain.permission.platform;

/**
 * @author chensongmin
 * @description 平台模型
 * @date 2025/2/6
 */
import com.bantanger.codegen.processor.api.GenCreateRequest;
import com.bantanger.codegen.processor.api.GenFeign;
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

@GenVo(pkgName = "com.bantanger.domain.permission.platform.vo")
@GenCreator(pkgName = "com.bantanger.domain.permission.platform.creator")
@GenUpdater(pkgName = "com.bantanger.domain.permission.platform.updater")
@GenRepository(pkgName = "com.bantanger.domain.permission.platform.repository")
@GenService(pkgName = "com.bantanger.domain.permission.platform.service")
@GenServiceImpl(pkgName = "com.bantanger.domain.permission.platform.service")
@GenQuery(pkgName = "com.bantanger.domain.permission.platform.query")
@GenMapper(pkgName = "com.bantanger.domain.permission.platform.mapper")
@GenController(pkgName = "com.bantanger.trigger.http.permission", sourcePath = GenSourceConstants.GEN_CONTROLLER_SOURCE)
@GenCreateRequest(pkgName = "com.bantanger.api.permission.platform.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenUpdateRequest(pkgName = "com.bantanger.api.permission.platform.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenQueryRequest(pkgName = "com.bantanger.api.permission.platform.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenResponse(pkgName = "com.bantanger.api.permission.platform.response", sourcePath = GenSourceConstants.GEN_API_SOURCE)
//@GenFeign(pkgName = "com.bantanger.api.permission.platform.api.service",sourcePath = GenSourceConstants.GEN_API_SOURCE,serverName =)
@Entity
@Table(name = "platform")
@Data
public class Platform extends BaseJpaAggregate {

    @FieldDesc(name = "平台编码")
    private String code;

    @FieldDesc(name = "平台名称")
    @QueryItem(desc = "平台名称")
    private String name;

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