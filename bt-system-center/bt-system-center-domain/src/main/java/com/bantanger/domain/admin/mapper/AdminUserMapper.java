// --- Auto Generated by BanTanger ---
package com.bantanger.domain.admin.mapper;

import com.bantanger.api.admin.request.AdminUserCreateRequest;
import com.bantanger.api.admin.request.AdminUserQueryRequest;
import com.bantanger.api.admin.request.AdminUserUpdateRequest;
import com.bantanger.api.admin.response.AdminUserResponse;
import com.bantanger.common.mapper.DateMapper;
import com.bantanger.common.mapper.GenericEnumMapper;
import com.bantanger.domain.admin.AdminUser;
import com.bantanger.domain.admin.creator.AdminUserCreator;
import com.bantanger.domain.admin.query.AdminUserQuery;
import com.bantanger.domain.admin.updater.AdminUserUpdater;
import com.bantanger.domain.admin.vo.AdminUserVO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
      uses = {
            GenericEnumMapper.class,
            DateMapper.class
      }
)
public interface AdminUserMapper {
   AdminUserMapper INSTANCE = Mappers.getMapper(AdminUserMapper.class);

   AdminUser dtoToEntity(AdminUserCreator dto);

   AdminUserUpdater request2Updater(AdminUserUpdateRequest request);

   AdminUserCreator request2Dto(AdminUserCreateRequest request);

   AdminUserQuery request2Query(AdminUserQueryRequest request);

   AdminUserResponse vo2Response(AdminUserVO vo);

   default AdminUserResponse vo2CustomResponse(AdminUserVO vo) {
      AdminUserResponse response = vo2Response(vo);
      return response;
   }
}
