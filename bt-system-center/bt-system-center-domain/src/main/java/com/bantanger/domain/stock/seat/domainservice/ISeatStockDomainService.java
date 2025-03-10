package com.bantanger.domain.stock.seat.domainservice;

import com.bantanger.domain.stock.seat.domainservice.model.SeatInfoModel;

import java.util.List;
import java.util.Set;

/**
 * @author chensongmin
 * @created 2025/3/6
 */

public interface ISeatStockDomainService {

    /**
     * 预占座位库存
     * @return stockId，用于前端和上下游执行解锁预占、核销、释放已售等操作
     */
    Long lockSeatStock(String showId, Long userId, List<SeatInfoModel> seatInfoModelList);

    /**
     * 解锁座位库存预占
     * @return
     */
    boolean unlockSeatStock(String showId, Long stockId, Set<String> seatNo);

    /**
     * 核销座位库存（已售）
     * @return
     */
    boolean submitSeatStock(String showId, Long stockId, Set<String> seatNo);

    /**
     * 释放已售座位库存
     * @return
     */
    boolean releaseSeatStock(String showId, Long stockId, Set<String> seatNo);

}
