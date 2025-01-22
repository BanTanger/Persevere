package com.bantanger.domain.asset.warehouse;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/22
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
import com.bantanger.common.constants.ValidStatus;
import com.bantanger.jpa.converter.ValidStatusConverter;
import com.bantanger.jpa.support.BaseJpaAggregate;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@GenVo(pkgName = "com.bantanger.domain.asset.warehouse.vo")
@GenCreator(pkgName = "com.bantanger.domain.asset.warehouse.creator")
@GenUpdater(pkgName = "com.bantanger.domain.asset.warehouse.updater")
@GenRepository(pkgName = "com.bantanger.domain.asset.warehouse.repository")
@GenService(pkgName = "com.bantanger.domain.asset.warehouse.service")
@GenServiceImpl(pkgName = "com.bantanger.domain.asset.warehouse.service")
@GenQuery(pkgName = "com.bantanger.domain.asset.warehouse.query")
@GenMapper(pkgName = "com.bantanger.domain.asset.warehouse.mapper")
@GenController(pkgName = "com.bantanger.trigger.http.asset", sourcePath = GenSourceConstants.GEN_CONTROLLER_SOURCE)
@GenCreateRequest(pkgName = "com.bantanger.api.asset.warehouse.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenUpdateRequest(pkgName = "com.bantanger.api.asset.warehouse.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenQueryRequest(pkgName = "com.bantanger.api.asset.warehouse.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenResponse(pkgName = "com.bantanger.api.asset.warehouse.response", sourcePath = GenSourceConstants.GEN_API_SOURCE)
//@GenFeign(pkgName = "com.bantanger.api.asset.warehouse.api.service",sourcePath = GenSourceConstants.GEN_API_SOURCE,serverName =)
@Entity
@Table(name = "warehouse")
@Data
public class Warehouse extends BaseJpaAggregate {

    @FieldDesc(name = "仓库名称")
    private String name;

    @FieldDesc(name = "仓库编码")
    private String code;

    @FieldDesc(name = "仓库地址")
    private String address;

    @FieldDesc(name = "创建人")
    private String createUser;

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
