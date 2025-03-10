package com.bantanger.domain.stock.seat;

/**
 * @author chensongmin
 * @created 2025/3/7
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
import com.bantanger.common.constants.GenSourceConstants;
import com.bantanger.common.enums.ValidStatus;
import com.bantanger.jpa.converter.ValidStatusConverter;
import com.bantanger.jpa.support.BaseJpaAggregate;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import lombok.Data;

@GenVo(pkgName = "com.bantanger.domain.stock.seat.vo")
@GenCreator(pkgName = "com.bantanger.domain.stock.seat.creator")
@GenUpdater(pkgName = "com.bantanger.domain.stock.seat.updater")
@GenRepository(pkgName = "com.bantanger.domain.stock.seat.repository")
@GenService(pkgName = "com.bantanger.domain.stock.seat.service")
@GenServiceImpl(pkgName = "com.bantanger.domain.stock.seat.service")
@GenQuery(pkgName = "com.bantanger.domain.stock.seat.query")
@GenMapper(pkgName = "com.bantanger.domain.stock.seat.mapper")
@GenController(pkgName = "com.bantanger.trigger.http.stock.seat", sourcePath = GenSourceConstants.GEN_CONTROLLER_SOURCE_MAC)
@GenCreateRequest(pkgName = "com.bantanger.api.stock.seat.request", sourcePath = GenSourceConstants.GEN_API_SOURCE_MAC)
@GenUpdateRequest(pkgName = "com.bantanger.api.stock.seat.request", sourcePath = GenSourceConstants.GEN_API_SOURCE_MAC)
@GenQueryRequest(pkgName = "com.bantanger.api.stock.seat.request", sourcePath = GenSourceConstants.GEN_API_SOURCE_MAC)
@GenResponse(pkgName = "com.bantanger.api.stock.seat.response", sourcePath = GenSourceConstants.GEN_API_SOURCE_MAC)
//@GenFeign(pkgName = "com.bantanger.api.stock.seat.api.service",sourcePath = GenSourceConstants.GEN_API_SOURCE,serverName =)
@Entity
@Data
public class SeatStock extends BaseJpaAggregate {

    @FieldDesc(name = "场次唯一标识")
    private String showId;

    @FieldDesc(name = "总库存数")
    private Integer totalNum;

    @FieldDesc(name = "已售数量")
    private Integer soldNum;

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