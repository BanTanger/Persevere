/*
 * Copyright (c) 2017 maoyan.com
 * All rights reserved.
 *
 */

package com.bantanger.domain.stock.seat.domainservice;

import com.bantanger.domain.stock.seat.domainservice.model.SeatInfoModel;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * @author chensongmin
 * @created 2025/3/7
 */
@Service
public class SeatStockDomainService implements ISeatStockDomainService {

    @Override
    public Long lockSeatStock(String showId, Long userId, List<SeatInfoModel> seatInfoModelList) {
        return 0L;
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