package com.bantanger.domain.asset.lifecycle;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/22
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
import com.bantanger.common.constants.ValidStatus;
import com.bantanger.domain.asset.record.enums.InOutBizType;
import com.bantanger.domain.asset.record.enums.InOutBizTypeConverter;
import com.bantanger.domain.asset.record.enums.InOutType;
import com.bantanger.domain.asset.record.enums.InOutTypeConverter;
import com.bantanger.jpa.converter.ValidStatusConverter;
import com.bantanger.jpa.support.BaseJpaAggregate;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@GenVo(pkgName = "com.bantanger.domain.asset.lifecycle.vo")
@GenCreator(pkgName = "com.bantanger.domain.asset.lifecycle.creator")
@GenUpdater(pkgName = "com.bantanger.domain.asset.lifecycle.updater")
@GenRepository(pkgName = "com.bantanger.domain.asset.lifecycle.repository")
@GenService(pkgName = "com.bantanger.domain.asset.lifecycle.service")
@GenServiceImpl(pkgName = "com.bantanger.domain.asset.lifecycle.service")
@GenQuery(pkgName = "com.bantanger.domain.asset.lifecycle.query")
@GenMapper(pkgName = "com.bantanger.domain.asset.lifecycle.mapper")
@GenController(pkgName = "com.bantanger.trigger.http.asset", sourcePath = GenSourceConstants.GEN_CONTROLLER_SOURCE)
@GenCreateRequest(pkgName = "com.bantanger.api.asset.lifecycle.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenUpdateRequest(pkgName = "com.bantanger.api.asset.lifecycle.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenQueryRequest(pkgName = "com.bantanger.api.asset.lifecycle.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenResponse(pkgName = "com.bantanger.api.asset.lifecycle.response", sourcePath = GenSourceConstants.GEN_API_SOURCE)
//@GenFeign(pkgName = "com.bantanger.trigger.asset.lifecycle.api.service",sourcePath = GenSourceConstants.GEN_API_SOURCE,serverName =)
@Entity
@Table(name = "asset_lifecycle")
@Data
public class AssetLifecycle extends BaseJpaAggregate {

    @FieldDesc(name = "资产名称")
    private String name;

    @FieldDesc(name = "资产ID")
    private Long assetsId;

    @FieldDesc(name = "资产唯一编码")
    private String uniqueCode;

    @FieldDesc(name = "额外信息")
    @Column(columnDefinition = "varchar(500)")
    private String remark;

    @FieldDesc(name = "仓库名称")
    private String houseName;

    @FieldDesc(name = "仓库ID")
    private Long houseId;

    @FieldDesc(name = "出入库业务类型")
    @Convert(converter = InOutBizTypeConverter.class)
    @TypeConverter(toTypeFullName = "java.lang.Integer")
    private InOutBizType inOutBizType;

    @FieldDesc(name = "出入库类型")
    @Convert(converter = InOutTypeConverter.class)
    @TypeConverter(toTypeFullName = "java.lang.Integer")
    private InOutType inOutType;

    @FieldDesc(name = "操作人")
    private String operateUser;

    @FieldDesc(name = "唯一批次号")
    private String genBatchNo;

    @FieldDesc(name = "批次号")
    private String batchNo;

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
