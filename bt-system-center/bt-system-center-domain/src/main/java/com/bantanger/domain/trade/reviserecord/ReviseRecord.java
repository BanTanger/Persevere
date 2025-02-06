package com.bantanger.domain.trade.reviserecord;

/**
 * @author chensongmin
 * @description 修订订单
 * @date 2025/1/24
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
import com.bantanger.common.enums.ValidStatus;
import com.bantanger.jpa.converter.ValidStatusConverter;
import com.bantanger.jpa.support.BaseJpaAggregate;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@GenVo(pkgName = "com.bantanger.domain.trade.reviserecord.vo")
@GenCreator(pkgName = "com.bantanger.domain.trade.reviserecord.creator")
@GenUpdater(pkgName = "com.bantanger.domain.trade.reviserecord.updater")
@GenRepository(pkgName = "com.bantanger.domain.trade.reviserecord.repository")
@GenService(pkgName = "com.bantanger.domain.trade.reviserecord.service")
@GenServiceImpl(pkgName = "com.bantanger.domain.trade.reviserecord.service")
@GenQuery(pkgName = "com.bantanger.domain.trade.reviserecord.query")
@GenMapper(pkgName = "com.bantanger.domain.trade.reviserecord.mapper")
@GenController(pkgName = "com.bantanger.trigger.http.trade", sourcePath = GenSourceConstants.GEN_CONTROLLER_SOURCE)
@GenCreateRequest(pkgName = "com.bantanger.api.trade.reviserecord.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenUpdateRequest(pkgName = "com.bantanger.api.trade.reviserecord.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenQueryRequest(pkgName = "com.bantanger.api.trade.reviserecord.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenResponse(pkgName = "com.bantanger.api.trade.reviserecord.response", sourcePath = GenSourceConstants.GEN_API_SOURCE)
//@GenFeign(pkgName = "com.bantanger.api.trade.reviserecord.api.service",sourcePath = GenSourceConstants.GEN_API_SOURCE,serverName =)
@Entity
@Table(name = "revise_record")
@Data
public class ReviseRecord extends BaseJpaAggregate {

    @FieldDesc(name = "操作人")
    private String operateUser;

    @FieldDesc(name = "唯一流水号")
    private Long flowId;

    @FieldDesc(name = "修订前")
    private String reviseBefore;

    @FieldDesc(name = "修订后")
    private String reviseAfter;

    // JSON 格式
    @FieldDesc(name = "变更点")
    private String diff;

    @FieldDesc(name = "变更原因")
    private String reviseReason;

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
