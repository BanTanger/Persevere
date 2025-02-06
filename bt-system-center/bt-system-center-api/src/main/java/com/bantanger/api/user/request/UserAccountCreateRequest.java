// --- Auto Generated by BanTanger ---
package com.bantanger.api.user.request;

import com.bantanger.common.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Integer;
import java.lang.Long;
import java.lang.String;

@Schema
public class UserAccountCreateRequest implements Request {
   @Schema(
         title = "用户ID"
   )
   private Long userId;

   @Schema(
         title = "账号ID"
   )
   private String accountNo;

   @Schema(
         title = "密码"
   )
   private String password;

   @Schema(
         title = "账号类别"
   )
   private Integer accountType;

   public Long getUserId() {
      return userId;
   }

   public void setUserId(Long userId) {
      this.userId = userId;
   }

   public String getAccountNo() {
      return accountNo;
   }

   public void setAccountNo(String accountNo) {
      this.accountNo = accountNo;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public Integer getAccountType() {
      return accountType;
   }

   public void setAccountType(Integer accountType) {
      this.accountType = accountType;
   }
}
