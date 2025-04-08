package com.bantanger.test;

import cn.hutool.core.util.IdUtil;
import com.bantanger.common.enums.ValidStatus;
import com.bantanger.domain.trade.order.OrderBase;
import com.bantanger.domain.trade.order.enums.OrderState;
import com.bantanger.domain.trade.order.enums.OrderType;
import com.bantanger.domain.trade.order.repository.OrderBaseRepository;
import com.bantanger.domain.user.AccountType;
import com.google.common.collect.ImmutableList;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.util.*;

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

    @Test
    public void test2() {
//        ImmutableList<List<String>> lists = ImmutableList.of(
//                List.of("A", "B", "C", "D"),
//                List.of("A", "D", "E", "F"),
//                List.of("A", "B", "C", "F")
//        );
        ImmutableList<List<String>> lists = ImmutableList.of(
                List.of("A", "B", "C", "F", "H"),
                List.of("A", "D", "C", "F"),
                List.of("A", "B", "E", "G")
        );
        Set<String> allDealIds = new LinkedHashSet<>();
        // 获取多个集合中长度最大的集合长度，即 max{list1.length, list2.length, list3.length}
//        int maxSize = lists.stream().mapToInt(List::size).max().orElse(0);
//        for (int i = 0; i < maxSize; i++) {
//            // 按照 “电影、演出、玩乐” 的顺序组织到 allDealIds 集合，如 A1,B1,C1,A2,B2,C2 ...
//            for (List<String> subCategoryDeals : lists) {
//                int j = 0;
//                while (j < subCategoryDeals.size() && !allDealIds.add(subCategoryDeals.get(j ++))) {}
//            }
//        }
        int maxSize = lists.stream().mapToInt(List::size).max().orElse(0);
        for (int i = 0; i < maxSize; i ++) {
            lists.forEach(list -> list.stream().filter(allDealIds::add).findFirst());
        }
        allDealIds.forEach(System.out::print);
    }

}
