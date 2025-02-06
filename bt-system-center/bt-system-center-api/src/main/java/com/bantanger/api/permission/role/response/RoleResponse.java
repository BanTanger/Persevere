// --- Auto Generated by BanTanger ---
package com.bantanger.api.permission.role.response;

import com.bantanger.common.enums.ValidStatus;
import com.bantanger.common.model.AbstractJpaResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Long;
import java.lang.String;

@Schema
public class RoleResponse extends AbstractJpaResponse {
   @Schema(
         title = "角色编码"
   )
   private String roleNo;

   @Schema(
         title = "角色名称"
   )
   private String name;

   @Schema(
         title = "平台ID"
   )
   private Long platformId;

   @Schema(
         title = "备注"
   )
   private String remark;

   @Schema(
         title = "validStatus"
   )
   private ValidStatus validStatus;

   public String getRoleNo() {
      return roleNo;
   }

   public void setRoleNo(String roleNo) {
      this.roleNo = roleNo;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Long getPlatformId() {
      return platformId;
   }

   public void setPlatformId(Long platformId) {
      this.platformId = platformId;
   }

   public String getRemark() {
      return remark;
   }

   public void setRemark(String remark) {
      this.remark = remark;
   }

   public ValidStatus getValidStatus() {
      return validStatus;
   }

   public void setValidStatus(ValidStatus validStatus) {
      this.validStatus = validStatus;
   }
}
