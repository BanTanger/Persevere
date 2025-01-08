// --- Auto Generated by BanTanger ---
package com.bantanger.api.trade.order.request;

import com.bantanger.common.model.Request;
import io.swagger.v3.oas.annotations.media.Schema;
import java.lang.Integer;

@Schema
public class OrderBaseQueryRequest implements Request {
   @Schema(
         title = "订单类型: 1-普通订单, 2-秒杀订单"
   )
   private Integer orderType;

   public Integer getOrderType() {
      return orderType;
   }

   public void setOrderType(Integer orderType) {
      this.orderType = orderType;
   }
}
