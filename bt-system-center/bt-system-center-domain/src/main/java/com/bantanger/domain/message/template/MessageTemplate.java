package com.bantanger.domain.message.template;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/27
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
import com.bantanger.common.annotation.FieldDesc;
import com.bantanger.common.annotation.TypeConverter;
import com.bantanger.common.constants.GenSourceConstants;
import com.bantanger.common.enums.ValidStatus;
import com.bantanger.domain.message.template.enums.TemplateType;
import com.bantanger.domain.message.template.enums.TemplateTypeConverter;
import com.bantanger.jpa.converter.ValidStatusConverter;
import com.bantanger.jpa.support.BaseJpaAggregate;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@GenVo(pkgName = "com.bantanger.domain.message.template.vo")
@GenCreator(pkgName = "com.bantanger.domain.message.template.creator")
@GenUpdater(pkgName = "com.bantanger.domain.message.template.updater")
@GenRepository(pkgName = "com.bantanger.domain.message.template.repository")
@GenService(pkgName = "com.bantanger.domain.message.template.service")
@GenServiceImpl(pkgName = "com.bantanger.domain.message.template.service")
@GenQuery(pkgName = "com.bantanger.domain.message.template.query")
@GenMapper(pkgName = "com.bantanger.domain.message.template.mapper")
@GenController(pkgName = "com.bantanger.trigger.http.message", sourcePath = GenSourceConstants.GEN_CONTROLLER_SOURCE_MAC)
@GenCreateRequest(pkgName = "com.bantanger.api.message.template.request", sourcePath = GenSourceConstants.GEN_API_SOURCE_MAC)
@GenUpdateRequest(pkgName = "com.bantanger.api.message.template.request", sourcePath = GenSourceConstants.GEN_API_SOURCE_MAC)
@GenQueryRequest(pkgName = "com.bantanger.api.message.template.request", sourcePath = GenSourceConstants.GEN_API_SOURCE_MAC)
@GenResponse(pkgName = "com.bantanger.api.message.template.response", sourcePath = GenSourceConstants.GEN_API_SOURCE_MAC)
//@GenFeign(pkgName = "com.bantanger.api.message.template.api.service",sourcePath = GenSourceConstants.GEN_API_SOURCE_MAC,serverName =)
@Entity
@Table(name = "message_template")
@Data
public class MessageTemplate extends BaseJpaAggregate {

    @FieldDesc(name = "模板编码")
    private String templateCode;

    @FieldDesc(name = "模板名称")
    private String name;

    @FieldDesc(name = "模板内容")
    private String template;

    @FieldDesc(name = "模板类型")
    @Convert(converter = TemplateTypeConverter.class)
    @TypeConverter
    private TemplateType templateType;

    @FieldDesc(name = "描述信息")
    private String description;

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
