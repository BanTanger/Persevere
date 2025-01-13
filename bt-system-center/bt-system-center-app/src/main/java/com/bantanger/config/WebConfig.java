package com.bantanger.config;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.LocalDateTimeUtil;
import cn.hutool.core.util.StrUtil;
import com.bantanger.intercepter.LogInterceptor;
import java.time.LocalDateTime;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author BanTanger 半糖
 * @date 2024/11/4
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 所有的请求都加上 Log 拦截器
        registry.addInterceptor(new LogInterceptor());
    }

//    @Bean
    public Converter<String, LocalDateTime> stringLocalDateTimeConverter() {
        return source -> {
            // 检查字符是否为数字
            if (StrUtil.isAllCharMatch(source, Character::isDigit)) {
                return LocalDateTimeUtil.of(Long.parseLong(source));
            } else {
                return DateUtil.parseLocalDateTime(source);
            }
        };
    }

}
