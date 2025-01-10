package com.bantanger.elegant.config;

import com.bantanger.elegant.filters.OrderLogSaveFilter;
import com.bantanger.elegant.filters.UserRiskFilter;
import com.bantanger.elegant.filters.UserValidFilter;
import com.bantanger.elegant.pipeline.EventFilter;
import com.bantanger.elegant.pipeline.FilterChainPipeline;
import com.bantanger.elegant.service.facade.IOrderPipelineFacadeService;
import javax.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/5
 */
@Configuration
public class OrderPipelineConfig {

    @Resource
    private IOrderPipelineFacadeService orderPipelineFacadeService;

    @Bean
    public FilterChainPipeline orderPipeline() {
        FilterChainPipeline<EventFilter> filterChainPipeline = new FilterChainPipeline<>();
        filterChainPipeline.addFirst(userValidFilter(), "用户合法性判断");
        filterChainPipeline.addFirst(userRiskFilter(), "用户风控判断");
        filterChainPipeline.addFirst(orderLogSaveFilter(), "下单操作日志存储");
        return filterChainPipeline;
    }

    @Bean
    public OrderLogSaveFilter orderLogSaveFilter() {
        return new OrderLogSaveFilter();
    }
    @Bean
    public UserRiskFilter userRiskFilter() {
        return new UserRiskFilter(orderPipelineFacadeService);
    }
    @Bean
    public UserValidFilter userValidFilter() {
        return new UserValidFilter(orderPipelineFacadeService);
    }
}
