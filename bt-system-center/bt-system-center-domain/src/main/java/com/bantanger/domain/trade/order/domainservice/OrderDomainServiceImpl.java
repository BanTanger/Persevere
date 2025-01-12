package com.bantanger.domain.trade.order.domainservice;

import cn.hutool.core.lang.Assert;
import com.bantanger.common.constants.CodeEnum;
import com.bantanger.common.exception.BusinessException;
import com.bantanger.domain.trade.order.OrderBase;
import com.bantanger.domain.trade.order.QOrderBase;
import com.bantanger.domain.trade.order.domainservice.model.OrderCompleteModel;
import com.bantanger.domain.trade.order.domainservice.model.OrderCreateModel;
import com.bantanger.domain.trade.order.domainservice.model.OrderItemModel;
import com.bantanger.domain.trade.order.domainservice.model.OrderReviseModel;
import com.bantanger.domain.trade.order.mapper.OrderBaseMapper;
import com.bantanger.domain.trade.order.repository.IFlowNoFacade;
import com.bantanger.domain.trade.order.repository.OrderBaseRepository;
import com.bantanger.jpa.support.EntityOperations;
import com.querydsl.core.BooleanBuilder;
import java.math.BigDecimal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/8
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class OrderDomainServiceImpl implements IOrderDomainService {

    private final OrderBaseRepository orderBaseRepository;
    private final IFlowNoFacade flowNoFacadeImpl;

    @Override
    public boolean orderCreate(OrderCreateModel orderCreateModel) {
        Assert.notEmpty(orderCreateModel.getItemInfoList());
        // 计算订单总金额
        BigDecimal itemTotal = orderCreateModel.getItemInfoList().stream()
            .map(OrderItemModel::getRealAmount)
            .reduce(BigDecimal.ZERO, BigDecimal::add);
        // 生成订单流水号
        Long nextId = flowNoFacadeImpl.getNextId();

        OrderBase orderBase = OrderBaseMapper.INSTANCE.model2Entity(orderCreateModel);
        orderBase.setFlowNo(nextId);
        orderBase.setTotalAmount(itemTotal);
        EntityOperations.doCreate(orderBaseRepository)
            .create(() -> orderBase)
            .update(e -> e.init(orderCreateModel))
            .execute();
        return false;
    }

    @Override
    public boolean orderComplete(OrderCompleteModel orderCompleteModel) {
        // querydsl 语法，相当于 where 后的条件
        BooleanBuilder builder = new BooleanBuilder().and(
            QOrderBase.orderBase.flowNo.eq(orderCompleteModel.getFlowNo()));
        // 查询订单
        OrderBase order = orderBaseRepository.findOne(builder)
            .orElseThrow(() -> new BusinessException(CodeEnum.NotFindError));

        EntityOperations.doUpdate(orderBaseRepository)
            .load(() -> order)
            .update(e -> e.complete(orderCompleteModel))
            .execute();
        return false;
    }

    @Override
    public boolean orderRevise(OrderReviseModel orderReviseModel) {
        // todo 暂时不实现
        return true;
    }

    @Override
    public boolean orderCancel(Long flowNo) {
        BooleanBuilder builder = new BooleanBuilder().and(QOrderBase.orderBase.flowNo.eq(flowNo));
        OrderBase order = orderBaseRepository.findOne(builder)
            .orElseThrow(() -> new BusinessException(CodeEnum.NotFindError));

        EntityOperations.doUpdate(orderBaseRepository)
            .load(() -> order)
            .update(e -> e.cancel())
            .execute();
        return true;
    }
}
