package com.bantanger.domain.stock.seat;

/**
 * @author chensongmin
 * @created 2025/3/6
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
import com.bantanger.common.converter.CodeValueListConverter;
import com.bantanger.common.enums.ValidStatus;
import com.bantanger.common.model.CodeValue;
import com.bantanger.domain.stock.seat.enums.SeatStockStatus;
import com.bantanger.domain.stock.seat.enums.SeatStockTypeConverter;
import com.bantanger.domain.stock.seat.enums.SeatType;
import com.bantanger.domain.stock.seat.enums.SeatTypeConverter;
import com.bantanger.jpa.converter.ValidStatusConverter;
import com.bantanger.jpa.support.BaseJpaAggregate;
import jakarta.persistence.Column;
import jakarta.persistence.Convert;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.time.Instant;
import java.util.List;

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
@Table(name = "seat_stock_detail") // 库存明细表
@Data
public class SeatStockDetail extends BaseJpaAggregate {

    @FieldDesc(name = "座位库存ID")
    private Long stockId;

    @FieldDesc(name = "用户ID")
    private Long userId;

    @FieldDesc(name = "场次唯一标识")
    private String showId;

    @FieldDesc(name = "在线座位唯一标识")
    private String seatNo;

    @FieldDesc(name = "在线座位库存状态")
    @Convert(converter = SeatStockTypeConverter.class)
    @TypeConverter
    private SeatStockStatus seatStockStatus;

    @FieldDesc(name = "座位类型")
    @Convert(converter = SeatTypeConverter.class)
    @TypeConverter
    private SeatType seatType;

    // json，存储座位详细数据
    @FieldDesc(name = "座位信息")
    @Column(columnDefinition = "text")
    private String seatInfo;

    @FieldDesc(name = "扩展信息")
    @Convert(converter = CodeValueListConverter.class)
    @Column(columnDefinition = "text")
    private List<CodeValue> extraInfo;

    @FieldDesc(name = "预占库存时间")
    private Instant lockTime;

    @FieldDesc(name = "释放库存时间")
    private Instant expireTime;

    @FieldDesc(name = "核销库存时间")
    private Instant submitTime;

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