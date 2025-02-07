package com.bantanger.domain.template.selectdict;

/**
 * @author chensongmin
 * @description 下拉字典模型
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
import com.bantanger.common.annotation.TypeConverter;
import com.bantanger.common.constants.GenSourceConstants;
import com.bantanger.common.converter.CodeValueListConverter;
import com.bantanger.common.enums.ValidStatus;
import com.bantanger.common.model.CodeValue;
import com.bantanger.jpa.converter.ValidStatusConverter;
import com.bantanger.jpa.support.BaseJpaAggregate;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import java.util.List;
import lombok.Data;

@GenVo(pkgName = "com.bantanger.domain.template.selectdict.vo")
@GenCreator(pkgName = "com.bantanger.domain.template.selectdict.creator")
@GenUpdater(pkgName = "com.bantanger.domain.template.selectdict.updater")
@GenRepository(pkgName = "com.bantanger.domain.template.selectdict.repository")
@GenService(pkgName = "com.bantanger.domain.template.selectdict.service")
@GenServiceImpl(pkgName = "com.bantanger.domain.template.selectdict.service")
@GenQuery(pkgName = "com.bantanger.domain.template.selectdict.query")
@GenMapper(pkgName = "com.bantanger.domain.template.selectdict.mapper")
@GenController(pkgName = "com.bantanger.trigger.http.template", sourcePath = GenSourceConstants.GEN_CONTROLLER_SOURCE)
@GenCreateRequest(pkgName = "com.bantanger.api.template.selectdict.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenUpdateRequest(pkgName = "com.bantanger.api.template.selectdict.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenQueryRequest(pkgName = "com.bantanger.api.template.selectdict.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenResponse(pkgName = "com.bantanger.api.template.selectdict.response", sourcePath = GenSourceConstants.GEN_API_SOURCE)
//@GenFeign(pkgName = "com.bantanger.api.template.selectdict.api.service",sourcePath = GenSourceConstants.GEN_API_SOURCE,serverName =)
@Entity
@Table(name = "select_dict")
@Data
public class SelectDict extends BaseJpaAggregate {

    @FieldDesc(name = "字典编码")
    private String code;

    @FieldDesc(name = "字典名称")
    private String name;

    @FieldDesc(name = "字典描述")
    private String desc;

    @FieldDesc(name = "字典类型")
    @Convert(converter = DictTypeConverter.class)
    @TypeConverter
    private DictType dictType;

    // 一些值的获取可能要通过远程接口回调【非必填】
    @FieldDesc(name = "HTTP接口地址")
    private String httpUrl;

    @FieldDesc(name = "选择值列表")
    @Convert(converter = CodeValueListConverter.class)
    private List<CodeValue> selectValues;

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
