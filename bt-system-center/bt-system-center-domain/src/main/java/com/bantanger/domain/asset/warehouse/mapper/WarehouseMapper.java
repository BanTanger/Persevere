// --- Auto Generated by BanTanger ---
package com.bantanger.domain.asset.warehouse.mapper;

import com.bantanger.api.asset.warehouse.request.WarehouseCreateRequest;
import com.bantanger.api.asset.warehouse.request.WarehouseQueryRequest;
import com.bantanger.api.asset.warehouse.request.WarehouseUpdateRequest;
import com.bantanger.api.asset.warehouse.response.WarehouseResponse;
import com.bantanger.common.mapper.DateMapper;
import com.bantanger.common.mapper.GenericEnumMapper;
import com.bantanger.domain.asset.warehouse.Warehouse;
import com.bantanger.domain.asset.warehouse.creator.WarehouseCreator;
import com.bantanger.domain.asset.warehouse.query.WarehouseQuery;
import com.bantanger.domain.asset.warehouse.updater.WarehouseUpdater;
import com.bantanger.domain.asset.warehouse.vo.WarehouseVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
      uses = {
            GenericEnumMapper.class,
            DateMapper.class
      }
)
public interface WarehouseMapper {
   WarehouseMapper INSTANCE = Mappers.getMapper(WarehouseMapper.class);

   Warehouse dtoToEntity(WarehouseCreator dto);

   WarehouseUpdater request2Updater(WarehouseUpdateRequest request);

   WarehouseCreator request2Dto(WarehouseCreateRequest request);

   WarehouseQuery request2Query(WarehouseQueryRequest request);

   WarehouseResponse vo2Response(WarehouseVO vo);

   default WarehouseResponse vo2CustomResponse(WarehouseVO vo) {
      WarehouseResponse response = vo2Response(vo);
      return response;
   }
}
