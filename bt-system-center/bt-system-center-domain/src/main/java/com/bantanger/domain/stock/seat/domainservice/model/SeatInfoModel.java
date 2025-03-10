package com.bantanger.domain.stock.seat.domainservice.model;

import com.bantanger.common.annotation.FieldDesc;
import com.bantanger.domain.stock.seat.enums.SeatType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author chensongmin
 * @created 2025/3/7
 */
@Setter
@Getter
@ToString
public class SeatInfoModel {

    @FieldDesc(name = "座位编码")
    private String seatNo;
    @FieldDesc(name = "座位行号（排）：数字或英文字符，如 5、A")
    private String rowId;
    @FieldDesc(name = "座位列号（列）：数字或英文字符，如 5、A")
    private String columnId;
    @FieldDesc(name = "座位类型")
    private SeatType seatType;
    @FieldDesc(name = "座位逻辑分区ID")
    private String sectionId;
    @FieldDesc(name = "座位逻辑分区Name")
    private String sectionName;
    @FieldDesc(name = "座位物理分区ID")
    private String regionId;
    @FieldDesc(name = "座位物理分区Name")
    private String regionName;

}