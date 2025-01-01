package com.bantanger.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/28
 */
@Configuration
@EnableJpaAuditing
public class JpaAuditingConfiguration {

    @Bean
    @ConditionalOnMissingBean(name = "myAuditorAware")
    MyAuditorAware myAuditorAware() {
        return new MyAuditorAware();
    }

}
