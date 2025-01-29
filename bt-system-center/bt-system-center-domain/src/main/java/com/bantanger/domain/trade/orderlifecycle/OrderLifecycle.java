package com.bantanger.domain.trade.orderlifecycle;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/23
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
import com.bantanger.common.annotation.TypeConverter;
import com.bantanger.common.constants.GenSourceConstants;
import com.bantanger.common.enums.ValidStatus;
import com.bantanger.domain.trade.orderlifecycle.enums.OrderOperateType;
import com.bantanger.domain.trade.orderlifecycle.enums.OrderOperateTypeConverter;
import com.bantanger.jpa.converter.ValidStatusConverter;
import com.bantanger.jpa.support.BaseJpaAggregate;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

@GenVo(pkgName = "com.bantanger.domain.trade.orderlifecycle.vo")
@GenCreator(pkgName = "com.bantanger.domain.trade.orderlifecycle.creator")
@GenUpdater(pkgName = "com.bantanger.domain.trade.orderlifecycle.updater")
@GenRepository(pkgName = "com.bantanger.domain.trade.orderlifecycle.repository")
@GenService(pkgName = "com.bantanger.domain.trade.orderlifecycle.service")
@GenServiceImpl(pkgName = "com.bantanger.domain.trade.orderlifecycle.service")
@GenQuery(pkgName = "com.bantanger.domain.trade.orderlifecycle.query")
@GenMapper(pkgName = "com.bantanger.domain.trade.orderlifecycle.mapper")
@GenController(pkgName = "com.bantanger.trigger.http.trade", sourcePath = GenSourceConstants.GEN_CONTROLLER_SOURCE)
@GenCreateRequest(pkgName = "com.bantanger.api.trade.orderlifecycle.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenUpdateRequest(pkgName = "com.bantanger.api.trade.orderlifecycle.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenQueryRequest(pkgName = "com.bantanger.api.trade.orderlifecycle.request", sourcePath = GenSourceConstants.GEN_API_SOURCE)
@GenResponse(pkgName = "com.bantanger.api.trade.orderlifecycle.response", sourcePath = GenSourceConstants.GEN_API_SOURCE)
//@GenFeign(pkgName = "com.bantanger.api.trade.orderlifecycle.api.service",sourcePath = GenSourceConstants.GEN_API_SOURCE,serverName =)
@Entity
@Table(name = "order_lifecycle")
@Data
public class OrderLifecycle extends BaseJpaAggregate {

    @FieldDesc(name = "唯一流水号")
    private Long flowNo;

    @FieldDesc(name = "操作类型")
    @Convert(converter = OrderOperateTypeConverter.class)
    @TypeConverter
    private OrderOperateType operateType;

    @FieldDesc(name = "额外信息")
    private String remark;

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
