// --- Auto Generated by BanTanger ---
package com.bantanger.api.permission.resource.request;

import com.bantanger.common.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.math.BigDecimal;

@Schema
public class ResourceCreateRequest implements Request {
   @Schema(
         title = "资源名称"
   )
   private String name;

   @Schema(
         title = "资源路径"
   )
   private String url;

   @Schema(
         title = "资源编码"
   )
   private String code;

   @Schema(
         title = "前端路由"
   )
   private String router;

   @Schema(
         title = "父节点"
   )
   private Long pid;

   @Schema(
         title = "排序编号"
   )
   private BigDecimal sortNum;

   @Schema(
         title = "icon名称"
   )
   private String iconClass;

   @Schema(
         title = "资源类别"
   )
   private Integer resourceType;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public String getUrl() {
      return url;
   }

   public void setUrl(String url) {
      this.url = url;
   }

   public String getCode() {
      return code;
   }

   public void setCode(String code) {
      this.code = code;
   }

   public String getRouter() {
      return router;
   }

   public void setRouter(String router) {
      this.router = router;
   }

   public Long getPid() {
      return pid;
   }

   public void setPid(Long pid) {
      this.pid = pid;
   }

   public BigDecimal getSortNum() {
      return sortNum;
   }

   public void setSortNum(BigDecimal sortNum) {
      this.sortNum = sortNum;
   }

   public String getIconClass() {
      return iconClass;
   }

   public void setIconClass(String iconClass) {
      this.iconClass = iconClass;
   }

   public Integer getResourceType() {
      return resourceType;
   }

   public void setResourceType(Integer resourceType) {
      this.resourceType = resourceType;
   }
}
