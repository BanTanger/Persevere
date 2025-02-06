package com.bantanger.domain.permission.resource;

/**
 * @author chensongmin
 * @description 资源模型
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
import com.bantanger.common.annotation.TypeConverter;
import com.bantanger.common.constants.GenSourceConstants;
import com.bantanger.common.enums.ValidStatus;
import com.bantanger.jpa.converter.ValidStatusConverter;
import com.bantanger.jpa.support.BaseJpaAggregate;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import java.math.BigDecimal;
import lombok.Data;

@GenVo(pkgName = "com.bantanger.domain.permission.resource.vo")
@GenCreator(pkgName = "com.bantanger.domain.permission.resource.creator")
@GenUpdater(pkgName = "com.bantanger.domain.permission.resource.updater")
@GenRepository(pkgName = "com.bantanger.domain.permission.resource.repository")
@GenService(pkgName = "com.bantanger.domain.permission.resource.service")
@GenServiceImpl(pkgName = "com.bantanger.domain.permission.resource.service")
@GenQuery(pkgName = "com.bantanger.domain.permission.resource.query")
@GenMapper(pkgName = "com.bantanger.domain.permission.resource.mapper")
@GenController(pkgName = "com.bantanger.trigger.http.permission", sourcePath = GenSourceConstants.GEN_CONTROLLER_SOURCE)
@GenCreateRequest(pkgName = "com.bantanger.api.permission.resource.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenUpdateRequest(pkgName = "com.bantanger.api.permission.resource.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenQueryRequest(pkgName = "com.bantanger.api.permission.resource.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenResponse(pkgName = "com.bantanger.api.permission.resource.response", sourcePath = GenSourceConstants.GEN_API_SOURCE)
//@GenFeign(pkgName = "com.bantanger.api.permission.resource.api.service",sourcePath = GenSourceConstants.GEN_API_SOURCE,serverName =)
@Entity
@Table(name = "resource")
@Data
public class Resource extends BaseJpaAggregate {

    @FieldDesc(name = "资源名称")
    @QueryItem
    @NotBlank(message = "资源名称不能为空")
    private String name;

    @FieldDesc(name = "资源路径")
    private String url;

    @FieldDesc(name = "资源编码")
    @QueryItem
    @NotBlank(message = "资源编码不能为空")
    private String code;

    @FieldDesc(name = "前端路由")
    private String router;

    @FieldDesc(name = "父节点")
    private Long pid;

    @FieldDesc(name = "排序编号")
    private BigDecimal sortNum;

    // 后端直接存储
    @FieldDesc(name = "icon名称")
    private String iconClass;

    @FieldDesc(name = "资源类别")
    @Convert(converter = ResourceTypeConverter.class)
    @TypeConverter
    private ResourceType resourceType;

    @FieldDesc(name = "资源描述")
    private String resourceDesc;

    @FieldDesc(name = "平台ID")
    private Long platformId;

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
