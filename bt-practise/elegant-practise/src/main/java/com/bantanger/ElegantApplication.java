package com.bantanger;

import com.bantanger.elegant.service.IOrderModelHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.plugin.core.config.EnablePluginRegistries;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/4
 */
@SpringBootApplication
@EnablePluginRegistries(value = {IOrderModelHandler.class})
public class ElegantApplication {

    public static void main(String[] args) {
        SpringApplication.run(ElegantApplication.class, args);
    }

}
