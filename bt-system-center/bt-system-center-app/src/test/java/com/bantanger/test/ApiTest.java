package com.bantanger.test;

import cn.hutool.core.util.IdUtil;
import com.bantanger.common.enums.ValidStatus;
import com.bantanger.domain.trade.order.OrderBase;
import com.bantanger.domain.trade.order.enums.OrderState;
import com.bantanger.domain.trade.order.enums.OrderType;
import com.bantanger.domain.trade.order.repository.OrderBaseRepository;
import com.bantanger.domain.user.AccountType;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class ApiTest {

    @Resource
    private OrderBaseRepository orderBaseRepository;

    @Test
    public void test() {
        log.info("测试完成");
    }

    @Test
    public void apiTest() {
        OrderBase orderBase = new OrderBase();
        orderBase.setTotalAmount(BigDecimal.valueOf(1.13));
        orderBase.setAccountType(AccountType.PERSONAL);
        orderBase.setFlowNo(IdUtil.getSnowflakeNextId());
        orderBase.setOrderState(OrderState.WAIT_PAY);
        orderBase.setOrderType(OrderType.SHOP);
        orderBase.setValidStatus(ValidStatus.VALID);
        orderBase.setInvoiceFlag(ValidStatus.INVALID);
        orderBaseRepository.save(orderBase);
    }

}
