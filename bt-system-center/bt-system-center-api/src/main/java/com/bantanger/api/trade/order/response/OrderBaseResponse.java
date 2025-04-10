// --- Auto Generated by BanTanger ---
package com.bantanger.api.trade.order.response;

import com.bantanger.common.enums.ValidStatus;
import com.bantanger.common.model.AbstractJpaResponse;
import com.bantanger.common.model.CodeValue;
import com.bantanger.pay.system.PayItem;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Integer;
import java.lang.Long;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Schema
public class OrderBaseResponse extends AbstractJpaResponse {
   @Schema(
         title = "唯一流水号"
   )
   private Long flowNo;

   @Schema(
         title = "订单金额"
   )
   private BigDecimal totalAmount;

   @Schema(
         title = "账号ID"
   )
   private Long accountId;

   @Schema(
         title = "账号类型: 1-个人, 2-企业"
   )
   private Integer accountType;

   @Schema(
         title = "订单类型: 1-普通订单, 2-秒杀订单"
   )
   private Integer orderType;

   @Schema(
         title = "支付详情(虚拟资产抵扣项)"
   )
   private List<PayItem> payItemList;

   @Schema(
         title = "待支付金额"
   )
   private BigDecimal waitPay;

   @Schema(
         title = "支付时间"
   )
   private Instant payTime;

   @Schema(
         title = "订单状态"
   )
   private Integer orderState;

   @Schema(
         title = "订单附加信息"
   )
   private List<CodeValue> attrs;

   @Schema(
         title = "是否开票"
   )
   private ValidStatus invoiceFlag;

   @Schema(
         title = "validStatus"
   )
   private ValidStatus validStatus;

   public Long getFlowNo() {
      return flowNo;
   }

   public void setFlowNo(Long flowNo) {
      this.flowNo = flowNo;
   }

   public BigDecimal getTotalAmount() {
      return totalAmount;
   }

   public void setTotalAmount(BigDecimal totalAmount) {
      this.totalAmount = totalAmount;
   }

   public Long getAccountId() {
      return accountId;
   }

   public void setAccountId(Long accountId) {
      this.accountId = accountId;
   }

   public Integer getAccountType() {
      return accountType;
   }

   public void setAccountType(Integer accountType) {
      this.accountType = accountType;
   }

   public Integer getOrderType() {
      return orderType;
   }

   public void setOrderType(Integer orderType) {
      this.orderType = orderType;
   }

   public List<PayItem> getPayItemList() {
      return payItemList;
   }

   public void setPayItemList(List<PayItem> payItemList) {
      this.payItemList = payItemList;
   }

   public BigDecimal getWaitPay() {
      return waitPay;
   }

   public void setWaitPay(BigDecimal waitPay) {
      this.waitPay = waitPay;
   }

   public Instant getPayTime() {
      return payTime;
   }

   public void setPayTime(Instant payTime) {
      this.payTime = payTime;
   }

   public Integer getOrderState() {
      return orderState;
   }

   public void setOrderState(Integer orderState) {
      this.orderState = orderState;
   }

   public List<CodeValue> getAttrs() {
      return attrs;
   }

   public void setAttrs(List<CodeValue> attrs) {
      this.attrs = attrs;
   }

   public ValidStatus getInvoiceFlag() {
      return invoiceFlag;
   }

   public void setInvoiceFlag(ValidStatus invoiceFlag) {
      this.invoiceFlag = invoiceFlag;
   }

   public ValidStatus getValidStatus() {
      return validStatus;
   }

   public void setValidStatus(ValidStatus validStatus) {
      this.validStatus = validStatus;
   }
}
