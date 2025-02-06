// --- Auto Generated by BanTanger ---
package com.bantanger.trigger.http.permission;

import com.bantanger.api.permission.role.request.RoleCreateRequest;
import com.bantanger.api.permission.role.request.RoleQueryRequest;
import com.bantanger.api.permission.role.request.RoleUpdateRequest;
import com.bantanger.api.permission.role.response.RoleResponse;
import com.bantanger.common.enums.CodeEnum;
import com.bantanger.common.model.JsonObject;
import com.bantanger.common.model.PageRequestWrapper;
import com.bantanger.common.model.PageResult;
import com.bantanger.domain.permission.role.creator.RoleCreator;
import com.bantanger.domain.permission.role.mapper.RoleMapper;
import com.bantanger.domain.permission.role.query.RoleQuery;
import com.bantanger.domain.permission.role.service.IRoleService;
import com.bantanger.domain.permission.role.updater.RoleUpdater;
import com.bantanger.domain.permission.role.vo.RoleVO;
import java.lang.Long;
import java.lang.String;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("role/v1/")
@RequiredArgsConstructor
public class RoleController {
   private final IRoleService roleService;

   /**
    * createRequest
    */
   @PostMapping("createRole")
   public JsonObject<Long> createRole(@RequestBody RoleCreateRequest request) {
      RoleCreator creator = RoleMapper.INSTANCE.request2Dto(request);
      return JsonObject.success(roleService.createRole(creator));
   }

   /**
    * update request
    */
   @PostMapping("updateRole")
   public JsonObject<String> updateRole(@RequestBody RoleUpdateRequest request) {
      RoleUpdater updater = RoleMapper.INSTANCE.request2Updater(request);
      roleService.updateRole(updater);
      return JsonObject.success(CodeEnum.Success.getName());
   }

   /**
    * valid
    */
   @PostMapping("valid/{id}")
   public JsonObject<String> validRole(@PathVariable Long id) {
      roleService.validRole(id);
      return JsonObject.success(CodeEnum.Success.getName());
   }

   /**
    * invalid
    */
   @PostMapping("invalid/{id}")
   public JsonObject<String> invalidRole(@PathVariable Long id) {
      roleService.invalidRole(id);
      return JsonObject.success(CodeEnum.Success.getName());
   }

   /**
    * findById
    */
   @GetMapping("findById/{id}")
   public JsonObject<RoleResponse> findById(@PathVariable Long id) {
      RoleVO vo = roleService.findById(id);
      RoleResponse response = RoleMapper.INSTANCE.vo2CustomResponse(vo);
      return JsonObject.success(response);
   }

   /**
    * findByPage request
    */
   @PostMapping("findByPage")
   public JsonObject<PageResult<RoleResponse>> findByPage(
         @RequestBody PageRequestWrapper<RoleQueryRequest> request) {
      PageRequestWrapper<RoleQuery> wrapper = new PageRequestWrapper<>();
      wrapper.setBean(RoleMapper.INSTANCE.request2Query(request.getBean()));
      wrapper.setSorts(request.getSorts());
          wrapper.setPageSize(request.getPageSize());
          wrapper.setPage(request.getPage());
      Page<RoleVO> page = roleService.findByPage(wrapper);
      return JsonObject.success(
              PageResult.of(
                  page.getContent().stream()
                      .map(vo -> RoleMapper.INSTANCE.vo2CustomResponse(vo))
                      .collect(Collectors.toList()),
                  page.getTotalElements(),
                  page.getSize(),
                  page.getNumber())
          );
   }
}
