// --- Auto Generated by BanTanger ---
package com.bantanger.api.template.objecttemplate.request;

import com.bantanger.common.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Long;
import java.lang.String;

@Schema
public class ObjectTemplateCreateRequest implements Request {
   @Schema(
         title = "模板名称"
   )
   private String name;

   @Schema(
         title = "模板编码"
   )
   private String code;

   @Schema(
         title = "创始人"
   )
   private String createUser;

   @Schema(
         title = "模板分类ID"
   )
   private Long categoryId;

   @Schema(
         title = "描述信息"
   )
   private String desc;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getCode() {
      return code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getCreateUser() {
      return createUser;
   }

   public void setCreateUser(String createUser) {
      this.createUser = createUser;
   }

   public Long getCategoryId() {
      return categoryId;
   }

   public void setCategoryId(Long categoryId) {
      this.categoryId = categoryId;
   }

   public String getDesc() {
      return desc;
   }

   public void setDesc(String desc) {
      this.desc = desc;
   }
}
