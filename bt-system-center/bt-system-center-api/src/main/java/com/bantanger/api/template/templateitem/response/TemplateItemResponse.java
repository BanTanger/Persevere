// --- Auto Generated by BanTanger ---
package com.bantanger.api.template.templateitem.response;

import com.bantanger.common.enums.ValidStatus;
import com.bantanger.common.model.AbstractJpaResponse;
import com.bantanger.common.model.CodeValue;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;
import java.math.BigDecimal;
import java.util.List;

@Schema
public class TemplateItemResponse extends AbstractJpaResponse {
   @Schema(
         title = "模板项名称"
   )
   private String name;

   @Schema(
         title = "输入类型"
   )
   private Integer inputType;

   @Schema(
         title = "模板项占位符"
   )
   private String placeHolder;

   @Schema(
         title = "模板项编码"
   )
   private String code;

   @Schema(
         title = "创建人"
   )
   private String createUser;

   @Schema(
         title = "排序编号"
   )
   private BigDecimal sortNum;

   @Schema(
         title = "备注"
   )
   private String remark;

   @Schema(
         title = "关联字典对应ID"
   )
   private Long dictId;

   @Schema(
         title = "扩展字段"
   )
   private List<CodeValue> extraList;

   @Schema(
         title = "是否必填"
   )
   private Integer requireFlag;

   @Schema(
         title = "validStatus"
   )
   private ValidStatus validStatus;

   public String getName() {
      return name;
   }

   public void setName(String name) {
      this.name = name;
   }

   public Integer getInputType() {
      return inputType;
   }

   public void setInputType(Integer inputType) {
      this.inputType = inputType;
   }

   public String getPlaceHolder() {
      return placeHolder;
   }

   public void setPlaceHolder(String placeHolder) {
      this.placeHolder = placeHolder;
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

   public BigDecimal getSortNum() {
      return sortNum;
   }

   public void setSortNum(BigDecimal sortNum) {
      this.sortNum = sortNum;
   }

   public String getRemark() {
      return remark;
   }

   public void setRemark(String remark) {
      this.remark = remark;
   }

   public Long getDictId() {
      return dictId;
   }

   public void setDictId(Long dictId) {
      this.dictId = dictId;
   }

   public List<CodeValue> getExtraList() {
      return extraList;
   }

   public void setExtraList(List<CodeValue> extraList) {
      this.extraList = extraList;
   }

   public Integer getRequireFlag() {
      return requireFlag;
   }

   public void setRequireFlag(Integer requireFlag) {
      this.requireFlag = requireFlag;
   }

   public ValidStatus getValidStatus() {
      return validStatus;
   }

   public void setValidStatus(ValidStatus validStatus) {
      this.validStatus = validStatus;
   }
}
