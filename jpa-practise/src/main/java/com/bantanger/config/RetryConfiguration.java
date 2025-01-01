package com.bantanger.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/28
 */
@EnableRetry
@Configuration
@PropertySource("classpath:retryConfig.properties")
public class RetryConfiguration {
}
