// --- Auto Generated by BanTanger ---
package com.bantanger.domain.template.verifyrule.mapper;

import com.bantanger.api.template.verifyrule.request.VerifyRuleConfigCreateRequest;
import com.bantanger.api.template.verifyrule.request.VerifyRuleConfigQueryRequest;
import com.bantanger.api.template.verifyrule.request.VerifyRuleConfigUpdateRequest;
import com.bantanger.api.template.verifyrule.response.VerifyRuleConfigResponse;
import com.bantanger.common.mapper.DateMapper;
import com.bantanger.common.mapper.GenericEnumMapper;
import com.bantanger.domain.template.verifyrule.VerifyRuleConfig;
import com.bantanger.domain.template.verifyrule.creator.VerifyRuleConfigCreator;
import com.bantanger.domain.template.verifyrule.query.VerifyRuleConfigQuery;
import com.bantanger.domain.template.verifyrule.updater.VerifyRuleConfigUpdater;
import com.bantanger.domain.template.verifyrule.vo.VerifyRuleConfigVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
      uses = {
            GenericEnumMapper.class,
            DateMapper.class
      }
)
public interface VerifyRuleConfigMapper {
   VerifyRuleConfigMapper INSTANCE = Mappers.getMapper(VerifyRuleConfigMapper.class);

   VerifyRuleConfig dtoToEntity(VerifyRuleConfigCreator dto);

   VerifyRuleConfigUpdater request2Updater(VerifyRuleConfigUpdateRequest request);

   VerifyRuleConfigCreator request2Dto(VerifyRuleConfigCreateRequest request);

   VerifyRuleConfigQuery request2Query(VerifyRuleConfigQueryRequest request);

   VerifyRuleConfigResponse vo2Response(VerifyRuleConfigVO vo);

   default VerifyRuleConfigResponse vo2CustomResponse(VerifyRuleConfigVO vo) {
      VerifyRuleConfigResponse response = vo2Response(vo);
      return response;
   }
}
