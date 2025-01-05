package com.bantanger.elegant.service.facade.impl;

import com.bantanger.elegant.service.facade.IRiskFacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/5
 */
@Service
@RequiredArgsConstructor
public class RiskFacadeServiceImpl implements IRiskFacadeService {

    // 模拟 RPC 调用
    // private final Rpc<RiskService> riskServiceRpc;
    @Override
    public boolean isRickUser(String userId) {
        // return riskServiceRpc.isRickUser(userId);

        // 这里直接模拟非风控用户了
        return false;
    }
}
