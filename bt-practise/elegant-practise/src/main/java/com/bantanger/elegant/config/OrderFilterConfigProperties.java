package com.bantanger.elegant.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/5
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "filters.order")
public class OrderFilterConfigProperties {

    private Map<String, List<String>> configs;
}
