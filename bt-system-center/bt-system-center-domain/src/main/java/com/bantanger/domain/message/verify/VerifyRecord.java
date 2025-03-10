package com.bantanger.domain.message.verify;

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

@GenVo(pkgName = "com.bantanger.domain.message.verify.vo")
@GenCreator(pkgName = "com.bantanger.domain.message.verify.creator")
@GenUpdater(pkgName = "com.bantanger.domain.message.verify.updater")
@GenRepository(pkgName = "com.bantanger.domain.message.verify.repository")
@GenService(pkgName = "com.bantanger.domain.message.verify.service")
@GenServiceImpl(pkgName = "com.bantanger.domain.message.verify.service")
@GenQuery(pkgName = "com.bantanger.domain.message.verify.query")
@GenMapper(pkgName = "com.bantanger.domain.message.verify.mapper")
@GenController(pkgName = "com.bantanger.trigger.http.message", sourcePath = GenSourceConstants.GEN_CONTROLLER_SOURCE_MAC)
@GenCreateRequest(pkgName = "com.bantanger.api.message.verify.request", sourcePath = GenSourceConstants.GEN_API_SOURCE_MAC)
@GenUpdateRequest(pkgName = "com.bantanger.api.message.verify.request", sourcePath = GenSourceConstants.GEN_API_SOURCE_MAC)
@GenQueryRequest(pkgName = "com.bantanger.api.message.verify.request", sourcePath = GenSourceConstants.GEN_API_SOURCE_MAC)
@GenResponse(pkgName = "com.bantanger.api.message.verify.response", sourcePath = GenSourceConstants.GEN_API_SOURCE_MAC)
//@GenFeign(pkgName = "com.bantanger.api.message.verify.api.service",sourcePath = GenSourceConstants.GEN_API_SOURCE,serverName =)
@Entity
@Table(name = "verify_record")
@Data
public class VerifyRecord extends BaseJpaAggregate {

    @FieldDesc(name = "目的账号")
    private String account;

    @FieldDesc(name = "发送内容")
    private String content;

    @FieldDesc(name = "有效期")
    private Long endTime;

    @FieldDesc(name = "验证码")
    private String verifyCode;

    @FieldDesc(name = "模板编码")
    private String templateCode;

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
