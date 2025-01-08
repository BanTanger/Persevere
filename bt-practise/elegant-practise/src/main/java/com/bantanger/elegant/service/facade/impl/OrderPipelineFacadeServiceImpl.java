package com.bantanger.elegant.service.facade.impl;

import com.bantanger.elegant.service.facade.IOrderPipelineFacadeService;
import com.bantanger.elegant.service.facade.IRiskFacadeService;
import com.bantanger.elegant.service.facade.IUserFacadeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/5
 */
@Service
@RequiredArgsConstructor
public class OrderPipelineFacadeServiceImpl implements IOrderPipelineFacadeService {

    private final IRiskFacadeService riskFacadeService;
    private final IUserFacadeService userFacadeService;

    @Override
    public IRiskFacadeService getRiskFacadeService() {
        return riskFacadeService;
    }

    @Override
    public IUserFacadeService getUserFacadeService() {
        return userFacadeService;
    }
}
