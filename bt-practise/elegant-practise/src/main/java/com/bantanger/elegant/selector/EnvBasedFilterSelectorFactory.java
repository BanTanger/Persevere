package com.bantanger.elegant.selector;

import com.bantanger.elegant.config.OrderFilterConfigProperties;
import com.bantanger.elegant.order.BizLineEnum;
import com.bantanger.elegant.order.OrderRequest;
import com.bantanger.elegant.pipeline.selector.FilterSelector;
import com.bantanger.elegant.pipeline.selector.LocalListBasedFilterSelector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.bantanger.elegant.order.BizLineEnum.YW1;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/5
 */
@Slf4j
@RequiredArgsConstructor
@Component
public class EnvBasedFilterSelectorFactory implements OrderFilterSelectorFactory {

    private final OrderFilterConfigProperties properties;

    @Override
    public FilterSelector getFilterSelector(OrderRequest orderRequest) {
        BizLineEnum bizCode = orderRequest.getBizCode();
        if (YW1.equals(bizCode)) {
            List<String> filterNames = properties.getConfigs()
                    .getOrDefault(YW1.name(), Collections.unmodifiableList(new ArrayList<>()));
            return new LocalListBasedFilterSelector(filterNames);
        }
        return null;
    }
}
