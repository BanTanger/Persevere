package com.bantanger.domain.asset.record;

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
import com.bantanger.common.annotation.TypeConverter;
import com.bantanger.common.constants.GenSourceConstants;
import com.bantanger.common.constants.ValidStatus;
import com.bantanger.domain.asset.record.enums.InOutBizType;
import com.bantanger.domain.asset.record.enums.InOutBizTypeConverter;
import com.bantanger.domain.asset.record.enums.InOutType;
import com.bantanger.domain.asset.record.enums.InOutTypeConverter;
import com.bantanger.jpa.converter.ValidStatusConverter;
import com.bantanger.jpa.support.BaseJpaAggregate;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@GenVo(pkgName = "com.bantanger.domain.asset.record.vo")
@GenCreator(pkgName = "com.bantanger.domain.asset.record.creator")
@GenUpdater(pkgName = "com.bantanger.domain.asset.record.updater")
@GenRepository(pkgName = "com.bantanger.domain.asset.record.repository")
@GenService(pkgName = "com.bantanger.domain.asset.record.service")
@GenServiceImpl(pkgName = "com.bantanger.domain.asset.record.service")
@GenQuery(pkgName = "com.bantanger.domain.asset.record.query")
@GenMapper(pkgName = "com.bantanger.domain.asset.record.mapper")
@GenController(pkgName = "com.bantanger.trigger.http.asset", sourcePath = GenSourceConstants.GEN_CONTROLLER_SOURCE)
@GenCreateRequest(pkgName = "com.bantanger.api.asset.record.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenUpdateRequest(pkgName = "com.bantanger.api.asset.record.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenQueryRequest(pkgName = "com.bantanger.api.asset.record.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenResponse(pkgName = "com.bantanger.api.asset.record.response", sourcePath = GenSourceConstants.GEN_API_SOURCE)
//@GenFeign(pkgName = "com.bantanger.api.asset.record.api.service",sourcePath = GenSourceConstants.GEN_API_SOURCE,serverName =)
@Entity
@Table(name = "asset_in_out_record")
@Data
public class AssetInOutRecord extends BaseJpaAggregate {

    @FieldDesc(name = "手动录入批次号,仅用于展示")
    private String batchNo;

    @FieldDesc(name = "自动生成的批次号，防止重复")
    private String genBatchNo;

    @FieldDesc(name = "出入库业务类型")
    @Convert(converter = InOutBizTypeConverter.class)
    @TypeConverter
    private InOutBizType inOutBizType;

    @FieldDesc(name = "出入库类型")
    @Convert(converter = InOutTypeConverter.class)
    @TypeConverter
    private InOutType inOutType;

    @FieldDesc(name = "总数量")
    private Integer totalCount;

    @FieldDesc(name = "操作人")
    private String operateUser;

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