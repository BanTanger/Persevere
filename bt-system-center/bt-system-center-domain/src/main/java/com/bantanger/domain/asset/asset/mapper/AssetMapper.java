// --- Auto Generated by BanTanger ---
package com.bantanger.domain.asset.asset.mapper;

import com.bantanger.api.asset.asset.request.AssetCreateRequest;
import com.bantanger.api.asset.asset.request.AssetQueryRequest;
import com.bantanger.api.asset.asset.request.AssetUpdateRequest;
import com.bantanger.api.asset.asset.response.AssetResponse;
import com.bantanger.common.mapper.DateMapper;
import com.bantanger.common.mapper.GenericEnumMapper;
import com.bantanger.domain.asset.asset.Asset;
import com.bantanger.domain.asset.asset.creator.AssetCreator;
import com.bantanger.domain.asset.asset.query.AssetQuery;
import com.bantanger.domain.asset.asset.updater.AssetUpdater;
import com.bantanger.domain.asset.asset.vo.AssetVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
      uses = {
            GenericEnumMapper.class,
            DateMapper.class
      }
)
public interface AssetMapper {
   AssetMapper INSTANCE = Mappers.getMapper(AssetMapper.class);

   Asset dtoToEntity(AssetCreator dto);

   AssetUpdater request2Updater(AssetUpdateRequest request);

   AssetCreator request2Dto(AssetCreateRequest request);

   AssetQuery request2Query(AssetQueryRequest request);

   AssetResponse vo2Response(AssetVO vo);

   default AssetResponse vo2CustomResponse(AssetVO vo) {
      AssetResponse response = vo2Response(vo);
      return response;
   }
}
