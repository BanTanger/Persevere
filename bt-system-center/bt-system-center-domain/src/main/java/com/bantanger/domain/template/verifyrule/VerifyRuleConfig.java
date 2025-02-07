package com.bantanger.domain.template.verifyrule;

/**
 * @author chensongmin
 * @description
 * @date 2025/2/7
 */
import com.bantanger.codegen.processor.api.GenCreateRequest;
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
import com.bantanger.common.converter.VerifyRuleListConverter;
import com.bantanger.common.enums.ValidStatus;
import com.bantanger.common.verifyrule.VerifyRule;
import com.bantanger.jpa.converter.ValidStatusConverter;
import com.bantanger.jpa.support.BaseJpaAggregate;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;

@GenVo(pkgName = "com.bantanger.domain.template.verifyrule.vo")
@GenCreator(pkgName = "com.bantanger.domain.template.verifyrule.creator")
@GenUpdater(pkgName = "com.bantanger.domain.template.verifyrule.updater")
@GenRepository(pkgName = "com.bantanger.domain.template.verifyrule.repository")
@GenService(pkgName = "com.bantanger.domain.template.verifyrule.service")
@GenServiceImpl(pkgName = "com.bantanger.domain.template.verifyrule.service")
@GenQuery(pkgName = "com.bantanger.domain.template.verifyrule.query")
@GenMapper(pkgName = "com.bantanger.domain.template.verifyrule.mapper")
@GenController(pkgName = "com.bantanger.trigger.http.template", sourcePath = GenSourceConstants.GEN_CONTROLLER_SOURCE)
@GenCreateRequest(pkgName = "com.bantanger.api.template.verifyrule.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenUpdateRequest(pkgName = "com.bantanger.api.template.verifyrule.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenQueryRequest(pkgName = "com.bantanger.api.template.verifyrule.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenResponse(pkgName = "com.bantanger.api.template.verifyrule.response", sourcePath = GenSourceConstants.GEN_API_SOURCE)
//@GenFeign(pkgName = "com.bantanger.api.template.verifyrule.api.service",sourcePath = GenSourceConstants.GEN_API_SOURCE,serverName =)
@Entity
@Table(name = "verify_rule_config")
@Data
public class VerifyRuleConfig extends BaseJpaAggregate {

    @FieldDesc(name = "校验规则名称")
    private String name;

    @FieldDesc(name = "对应模板项ID")
    private Long itemId;

    @FieldDesc(name = "描述信息")
    private String desc;

    @FieldDesc(name = "校验规则配置列表")
    @Convert(converter = VerifyRuleListConverter.class)
    @Column(columnDefinition = "text")
    private List<VerifyRule> ruleList;

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
