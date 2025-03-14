// --- Auto Generated by BanTanger ---
package com.bantanger.api.message.verify.response;

import com.bantanger.common.enums.ValidStatus;
import com.bantanger.common.model.AbstractJpaResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Long;
import java.lang.String;

@Schema
public class VerifyRecordResponse extends AbstractJpaResponse {
   @Schema(
         title = "目的账号"
   )
   private String account;

   @Schema(
         title = "发送内容"
   )
   private String content;

   @Schema(
         title = "有效期"
   )
   private Long endTime;

   @Schema(
         title = "验证码"
   )
   private String verifyCode;

   @Schema(
         title = "模板编码"
   )
   private String templateCode;

   @Schema(
         title = "validStatus"
   )
   private ValidStatus validStatus;

   public String getAccount() {
      return account;
   }

   public void setAccount(String account) {
      this.account = account;
   }

   public String getContent() {
      return content;
   }

   public void setContent(String content) {
      this.content = content;
   }

   public Long getEndTime() {
      return endTime;
   }

   public void setEndTime(Long endTime) {
      this.endTime = endTime;
   }

   public String getVerifyCode() {
      return verifyCode;
   }

   public void setVerifyCode(String verifyCode) {
      this.verifyCode = verifyCode;
   }

   public String getTemplateCode() {
      return templateCode;
   }

   public void setTemplateCode(String templateCode) {
      this.templateCode = templateCode;
   }

   public ValidStatus getValidStatus() {
      return validStatus;
   }

   public void setValidStatus(ValidStatus validStatus) {
      this.validStatus = validStatus;
   }
}
