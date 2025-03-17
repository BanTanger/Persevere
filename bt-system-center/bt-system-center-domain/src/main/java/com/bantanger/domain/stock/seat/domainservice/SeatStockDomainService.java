/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */

package com.bantanger.domain.stock.seat.domainservice;

import com.bantanger.common.errortype.SeatStockErrorType;
import com.bantanger.common.exception.BusinessException;
import com.bantanger.domain.stock.seat.domainservice.model.SeatInfoModel;
import io.vavr.control.Try;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author chensongmin
 * @created 2025/3/7
 */
@Slf4j
@Service
public class SeatStockDomainService implements ISeatStockDomainService {

    /**
     * <pre>
     * 预占库存
     * 1. 获取分布式锁：失败释放锁，预占失败
     * 2. 判断该座位是否已售
     * 3. 购买记录插入流水表
     * 4. 发送 mq 消息异步修改预占库存座位图
     * </pre>
     *
     * @param showId
     * @param userId
     * @param seatInfoModelList
     * @return
     */
    @Override
    public Long lockSeatStock(String showId, Long userId, List<SeatInfoModel> seatInfoModelList) {
        Set<String> seatNos = seatInfoModelList.stream().map(SeatInfoModel::getSeatNo).collect(Collectors.toSet());
        Try.of(() -> batchSetNx4SeatNos(showId, seatNos))
                .onSuccess(u -> log.info("批量获取座位分布式锁成功，showId:{}, userId:{}, seatNos:{}", showId, userId, seatNos))
                .onFailure(e -> {
                    log.error("批量获取座位分布式锁失败, showId:{}, userId:{}, seatNos:{}", showId, userId, seatNos, e);
                    throw new BusinessException(SeatStockErrorType.SEAT_STOCK_ALREADY_LOCKED_CACHE);
                });

        return 0L;
    }

    private boolean batchSetNx4SeatNos(String showId, Set<String> seatNos) {
        // 因为是批量对 seatNos 加锁，所以需要对座位号集合排序，
        // 避免并发场景下多个用户的锁座集合间存在交集所导致死锁的问题
        List<String> sortedSeatNos = seatNos.stream().sorted(String::compareTo).toList();

        // todo redis 批量加锁

        return false;
    }

    @Override
    public boolean unlockSeatStock(String showId, Long stockId, Set<String> seatNo) {
        return false;
    }

    @Override
    public boolean submitSeatStock(String showId, Long stockId, Set<String> seatNo) {
        return false;
    }

    @Override
    public boolean releaseSeatStock(String showId, Long stockId, Set<String> seatNo) {
        return false;
    }
}