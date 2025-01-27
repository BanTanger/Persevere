package com.bantanger.extension.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author chensongmin
 * @description SPI 自动配置
 * @date 2025/1/27
 */
@ComponentScan(basePackages = {"com.bantanger.extension"})
@Configuration
public class ExtensionAutoConfiguration {

}
