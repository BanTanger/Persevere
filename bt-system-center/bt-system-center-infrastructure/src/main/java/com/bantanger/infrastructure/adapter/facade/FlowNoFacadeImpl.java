package com.bantanger.infrastructure.adapter.facade;

import cn.hutool.core.util.IdUtil;
import com.bantanger.domain.trade.order.repository.IFlowNoFacade;
import org.springframework.stereotype.Service;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/8
 */
@Service
public class FlowNoFacadeImpl implements IFlowNoFacade {

    @Override
    public Long getNextId() {
        return IdUtil.getSnowflake().nextId();
    }

}
