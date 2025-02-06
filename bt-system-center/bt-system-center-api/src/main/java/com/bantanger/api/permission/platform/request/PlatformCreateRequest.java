// --- Auto Generated by BanTanger ---
package com.bantanger.api.permission.platform.request;

import com.bantanger.common.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.String;

@Schema
public class PlatformCreateRequest implements Request {
   @Schema(
         title = "平台编码"
   )
   private String code;

   @Schema(
         title = "平台名称"
   )
   private String name;

   public String getCode() {
      return code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }
}
