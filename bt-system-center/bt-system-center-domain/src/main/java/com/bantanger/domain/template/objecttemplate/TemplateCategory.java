package com.bantanger.domain.template.objecttemplate;

/**
 * @author chensongmin
 * @description 模板分类
 * @date 2025/2/7
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

@GenVo(pkgName = "com.bantanger.domain.template.objecttemplate.vo")
@GenCreator(pkgName = "com.bantanger.domain.template.objecttemplate.creator")
@GenUpdater(pkgName = "com.bantanger.domain.template.objecttemplate.updater")
@GenRepository(pkgName = "com.bantanger.domain.template.objecttemplate.repository")
@GenService(pkgName = "com.bantanger.domain.template.objecttemplate.service")
@GenServiceImpl(pkgName = "com.bantanger.domain.template.objecttemplate.service")
@GenQuery(pkgName = "com.bantanger.domain.template.objecttemplate.query")
@GenMapper(pkgName = "com.bantanger.domain.template.objecttemplate.mapper")
@GenController(pkgName = "com.bantanger.trigger.http.template", sourcePath = GenSourceConstants.GEN_CONTROLLER_SOURCE)
@GenCreateRequest(pkgName = "com.bantanger.api.template.objecttemplate.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenUpdateRequest(pkgName = "com.bantanger.api.template.objecttemplate.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenQueryRequest(pkgName = "com.bantanger.api.template.objecttemplate.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenResponse(pkgName = "com.bantanger.api.template.objecttemplate.response", sourcePath = GenSourceConstants.GEN_API_SOURCE)
//@GenFeign(pkgName = "com.bantanger.api.template.objecttemplate.api.service",sourcePath = GenSourceConstants.GEN_API_SOURCE,serverName =)
@Entity
@Table(name = "template_category")
@Data
public class TemplateCategory extends BaseJpaAggregate {

    @FieldDesc(name = "分类名称")
    private String name;

    @FieldDesc(name = "分类编码")
    private String code;

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
