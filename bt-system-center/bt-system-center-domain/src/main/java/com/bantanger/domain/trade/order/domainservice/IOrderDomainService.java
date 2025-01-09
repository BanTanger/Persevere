package com.bantanger.domain.trade.order.domainservice;

import com.bantanger.domain.trade.order.domainservice.model.OrderCompleteModel;
import com.bantanger.domain.trade.order.domainservice.model.OrderCreateModel;
import com.bantanger.domain.trade.order.domainservice.model.OrderReviseModel;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/8
 */
public interface IOrderDomainService {

    /**
     * 创建订单
     * @param orderCreateModel
     * @return
     */
    boolean orderCreate(OrderCreateModel orderCreateModel);

    /**
     * 订单完成
     * @param orderCompleteModel
     * @return
     */
    boolean orderComplete(OrderCompleteModel orderCompleteModel);

    /**
     * 订单修改
     * @param orderReviseModel
     * @return
     */
    boolean orderRevise(OrderReviseModel orderReviseModel);

    /**
     * 订单取消
     * @param flowNo
     * @return
     */
    boolean orderCancel(Long flowNo);

}
