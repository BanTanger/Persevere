package com.bantanger.extension.executor;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;

/**
 * @author chensongmin
 * @description
 * @date 2025/1/27
 */
@Component
public class ServiceSelectorExecutor extends AbstractServiceSelectorExecutor implements
    ApplicationContextAware, SmartInitializingSingleton {

    private ApplicationContext applicationContext;
    // 扩展点缓存
    private final Map<String, Object> extensionMap = new ConcurrentHashMap<>();

    /**
     * @param bizScene 业务场景，实为枚举
     * @param clazz    接口 Class
     * @param <S>
     * @return
     */
    @Override
    @SuppressWarnings("unchecked")
    protected <S> S selectBizScene(BizScene bizScene, Class<S> clazz) {
        return (S) Optional.ofNullable(extensionMap.get(bizScene.getBizId()))
            .orElseThrow(() -> new RuntimeException("not find service"));
    }

    /**
     * 在 Bean 初始化后，将 @Extension 注解的 Bean 缓存
     */
    @Override
    public void afterSingletonsInstantiated() {
        // 通过 applicationContext 上下文，获取 @Extension 注解的 Bean
        this.applicationContext.getBeansWithAnnotation(Extension.class)
            .forEach((k, v) -> {
                // v 就是 bean，获取 Bean 的 Class 对象
                Class<?> extensionClazz = v.getClass();
                // 如果是代理对象，获取代理对象的 Class 对象
                if (AopUtils.isAopProxy(v)) {
                    extensionClazz = ClassUtils.getUserClass(v);
                }
                // 获取 @Extension 注解
                Extension extensionAnno = extensionClazz.getAnnotation(Extension.class);
                // 将扩展点缓存
                Optional.ofNullable(extensionMap.putIfAbsent(extensionAnno.bizId(), v))
                    .ifPresent(pre -> {
                        throw new RuntimeException("bizId has exist!!");
                    });
            });
    }

    /**
     * @param applicationContext the ApplicationContext object to be used by this object
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
