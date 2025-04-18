// --- Auto Generated by BanTanger ---
package com.bantanger.domain.permission.platform.mapper;

import com.bantanger.api.permission.platform.request.PlatformCreateRequest;
import com.bantanger.api.permission.platform.request.PlatformQueryRequest;
import com.bantanger.api.permission.platform.request.PlatformUpdateRequest;
import com.bantanger.api.permission.platform.response.PlatformResponse;
import com.bantanger.common.mapper.DateMapper;
import com.bantanger.common.mapper.GenericEnumMapper;
import com.bantanger.domain.permission.platform.Platform;
import com.bantanger.domain.permission.platform.creator.PlatformCreator;
import com.bantanger.domain.permission.platform.query.PlatformQuery;
import com.bantanger.domain.permission.platform.updater.PlatformUpdater;
import com.bantanger.domain.permission.platform.vo.PlatformVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
      uses = {
            GenericEnumMapper.class,
            DateMapper.class
      }
)
public interface PlatformMapper {
   PlatformMapper INSTANCE = Mappers.getMapper(PlatformMapper.class);

   Platform dtoToEntity(PlatformCreator dto);

   PlatformUpdater request2Updater(PlatformUpdateRequest request);

   PlatformCreator request2Dto(PlatformCreateRequest request);

   PlatformQuery request2Query(PlatformQueryRequest request);

   PlatformResponse vo2Response(PlatformVO vo);

   default PlatformResponse vo2CustomResponse(PlatformVO vo) {
      PlatformResponse response = vo2Response(vo);
      return response;
   }
}
