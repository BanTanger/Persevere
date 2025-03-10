// --- Auto Generated by BanTanger ---
package com.bantanger.domain.stock.seat.mapper;

import com.bantanger.api.stock.seat.request.SeatStockCreateRequest;
import com.bantanger.api.stock.seat.request.SeatStockQueryRequest;
import com.bantanger.api.stock.seat.request.SeatStockUpdateRequest;
import com.bantanger.api.stock.seat.response.SeatStockResponse;
import com.bantanger.common.mapper.DateMapper;
import com.bantanger.common.mapper.GenericEnumMapper;
import com.bantanger.domain.CustomMapper;
import com.bantanger.domain.stock.seat.SeatStock;
import com.bantanger.domain.stock.seat.creator.SeatStockCreator;
import com.bantanger.domain.stock.seat.query.SeatStockQuery;
import com.bantanger.domain.stock.seat.updater.SeatStockUpdater;
import com.bantanger.domain.stock.seat.vo.SeatStockVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
        uses = {
                GenericEnumMapper.class,
                DateMapper.class,
                CustomMapper.class
        }
)
public interface SeatStockMapper {
    SeatStockMapper INSTANCE = Mappers.getMapper(SeatStockMapper.class);

    SeatStock dtoToEntity(SeatStockCreator dto);

    SeatStockUpdater request2Updater(SeatStockUpdateRequest request);

    SeatStockCreator request2Dto(SeatStockCreateRequest request);

    SeatStockQuery request2Query(SeatStockQueryRequest request);

    SeatStockResponse vo2Response(SeatStockVO vo);

    default SeatStockResponse vo2CustomResponse(SeatStockVO vo) {
        SeatStockResponse response = vo2Response(vo);
        return response;
    }
}
