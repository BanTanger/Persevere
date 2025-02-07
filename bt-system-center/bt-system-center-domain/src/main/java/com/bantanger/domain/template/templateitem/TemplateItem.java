package com.bantanger.domain.template.templateitem;

/**
 * @author chensongmin
 * @description
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
import com.bantanger.codegen.processor.query.QueryItem;
import com.bantanger.codegen.processor.repository.GenRepository;
import com.bantanger.codegen.processor.service.GenService;
import com.bantanger.codegen.processor.creator.IgnoreCreator;
import com.bantanger.codegen.processor.updater.IgnoreUpdater;
import com.bantanger.codegen.processor.service.GenServiceImpl;
import com.bantanger.codegen.processor.updater.GenUpdater;
import com.bantanger.codegen.processor.vo.GenVo;
import com.bantanger.common.annotation.FieldDesc;
import com.bantanger.common.annotation.TypeConverter;
import com.bantanger.common.constants.GenSourceConstants;
import com.bantanger.common.converter.CodeValueListConverter;
import com.bantanger.common.enums.ValidStatus;
import com.bantanger.common.model.CodeValue;
import com.bantanger.jpa.converter.ValidStatusConverter;
import com.bantanger.jpa.support.BaseJpaAggregate;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.List;
import lombok.Data;

@GenVo(pkgName = "com.bantanger.domain.template.templateitem.vo")
@GenCreator(pkgName = "com.bantanger.domain.template.templateitem.creator")
@GenUpdater(pkgName = "com.bantanger.domain.template.templateitem.updater")
@GenRepository(pkgName = "com.bantanger.domain.template.templateitem.repository")
@GenService(pkgName = "com.bantanger.domain.template.templateitem.service")
@GenServiceImpl(pkgName = "com.bantanger.domain.template.templateitem.service")
@GenQuery(pkgName = "com.bantanger.domain.template.templateitem.query")
@GenMapper(pkgName = "com.bantanger.domain.template.templateitem.mapper")
@GenController(pkgName = "com.bantanger.trigger.http.template", sourcePath = GenSourceConstants.GEN_CONTROLLER_SOURCE)
@GenCreateRequest(pkgName = "com.bantanger.api.template.templateitem.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenUpdateRequest(pkgName = "com.bantanger.api.template.templateitem.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenQueryRequest(pkgName = "com.bantanger.api.template.templateitem.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenResponse(pkgName = "com.bantanger.api.template.templateitem.response", sourcePath = GenSourceConstants.GEN_API_SOURCE)
//@GenFeign(pkgName = "com.bantanger.api.template.templateitem.api.service",sourcePath = GenSourceConstants.GEN_API_SOURCE,serverName =)
@Entity
@Table(name = "template_item")
@Data
public class TemplateItem extends BaseJpaAggregate {

    @QueryItem(desc = "模板项名称")
    @FieldDesc(name = "模板项名称")
    @NotBlank(message = "模板项名称不能为空")
    private String name;

    @FieldDesc(name = "输入类型")
    @Convert(converter = InputTypeConverter.class)
    @TypeConverter
    private InputType inputType;

    // 模板参数替换位置
    @FieldDesc(name = "模板项占位符")
    private String placeHolder;

    @FieldDesc(name = "模板项编码")
    private String code;

    @FieldDesc(name = "创建人")
    private String createUser;

    @FieldDesc(name = "排序编号")
    private BigDecimal sortNum;

    @FieldDesc(name = "备注")
    private String remark;

    // 下拉框字典表自由选择的关联
    @FieldDesc(name = "关联字典对应ID")
    private Long dictId;

    // json 数据存储，CQRS 通过 mq 投递给 ES 来做复杂查询
    @FieldDesc(name = "扩展字段")
    @Convert(converter = CodeValueListConverter.class)
    @Column(columnDefinition = "text")
    private List<CodeValue> extraList;

    @FieldDesc(name = "是否必填")
    @Convert(converter = ValidStatusConverter.class)
    @TypeConverter
    private ValidStatus requireFlag;

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
