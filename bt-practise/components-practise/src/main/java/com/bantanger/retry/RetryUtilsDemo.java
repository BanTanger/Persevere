package com.bantanger.retry;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RetryUtils使用示例
 * 
 * @author bantanger
 */
public class RetryUtilsDemo {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RetryUtilsDemo.class);
    private static final Random RANDOM = new Random();
    
    public static void main(String[] args) {
        // 示例1：基本用法 - 处理可能返回null的情况
        demoBasicRetry();
        
        // 示例2：使用RetryBuilder进行链式调用
        demoRetryBuilder();
        
        // 示例3：处理特定异常
        demoRetryForSpecificException();
        
        // 示例4：处理特定返回值
        demoRetryForSpecificResult();
        
        // 示例5：无返回值的重试
        demoRetryWithoutResult();
    }
    
    /**
     * 示例1：基本用法 - 处理可能返回null的情况
     */
    private static void demoBasicRetry() {
        LOGGER.info("=== 示例1：基本用法 - 处理可能返回null的情况 ===");
        
        try {
            // 使用默认配置执行重试，当返回值为null时会自动重试
            String result = RetryUtils.executeWithRetry(() -> {
                // 模拟调用第三方接口，有可能返回null
                return simulateApiCall();
            });
            
            LOGGER.info("最终结果: {}", result);
        } catch (Exception e) {
            LOGGER.error("重试失败: {}", e.getMessage(), e);
        }
    }
    
    /**
     * 示例2：使用RetryBuilder进行链式调用
     */
    private static void demoRetryBuilder() {
        LOGGER.info("=== 示例2：使用RetryBuilder进行链式调用 ===");
        
        try {
            // 使用RetryBuilder进行链式调用，自定义重试配置
            String result = RetryUtils.RetryBuilder.newBuilder(() -> simulateApiCall())
                    .withMaxRetryTimes(5)                // 设置最大重试次数为5
                    .withInitialInterval(500)            // 设置初始重试间隔为500毫秒
                    .withMaxInterval(5000)               // 设置最大重试间隔为5000毫秒
                    .withMultiplier(2.0)                 // 设置退避乘数为2.0
                    .execute();
            
            LOGGER.info("最终结果: {}", result);
        } catch (Exception e) {
            LOGGER.error("重试失败: {}", e.getMessage(), e);
        }
    }
    
    /**
     * 示例3：处理特定异常
     */
    private static void demoRetryForSpecificException() {
        LOGGER.info("=== 示例3：处理特定异常 ===");
        
        try {
            // 只有当发生TimeoutException时才进行重试
            String result = RetryUtils.RetryBuilder.newBuilder(() -> {
                    try {
                        return simulateApiCallWithException();
                    } catch (TimeoutException | IOException e) {
                        throw new RuntimeException(e);
                    }
                })
                    .retryOnException(TimeoutException.class)  // 只有TimeoutException才重试
                    .withMaxRetryTimes(3)
                    .execute();
            
            LOGGER.info("最终结果: {}", result);
        } catch (Exception e) {
            LOGGER.error("重试失败: {}", e.getMessage(), e);
        }
    }
    
    /**
     * 示例4：处理特定返回值
     */
    private static void demoRetryForSpecificResult() {
        LOGGER.info("=== 示例4：处理特定返回值 ===");
        
        try {
            // 当返回值为空或者包含"error"时进行重试
            String result = RetryUtils.RetryBuilder.newBuilder(() -> simulateApiCallWithErrorResult())
                    .retryIfResult(res -> res == null || res.contains("error"))  // 自定义失败条件
                    .withMaxRetryTimes(4)
                    .execute();
            
            LOGGER.info("最终结果: {}", result);
        } catch (Exception e) {
            LOGGER.error("重试失败: {}", e.getMessage(), e);
        }
    }
    
    /**
     * 示例5：无返回值的重试
     */
    private static void demoRetryWithoutResult() {
        LOGGER.info("=== 示例5：无返回值的重试 ===");
        
        try {
            // 执行无返回值的操作，发生异常时重试
            RetryUtils.executeWithRetry(() -> {
                simulateVoidMethodWithException();
            }, 3, 1000, 5000, 2.0);
            
            LOGGER.info("操作成功完成");
        } catch (Exception e) {
            LOGGER.error("重试失败: {}", e.getMessage(), e);
        }
    }
    
    /**
     * 模拟调用第三方接口，有时返回null
     */
    private static String simulateApiCall() {
        int randomValue = RANDOM.nextInt(10);
        LOGGER.info("模拟API调用，随机值: {}", randomValue);
        
        // 模拟有时返回null的情况
        if (randomValue < 7) {
            LOGGER.info("API返回null，需要重试");
            return null;
        } else {
            String result = "API调用成功，返回数据-" + randomValue;
            LOGGER.info(result);
            return result;
        }
    }
    
    /**
     * 模拟调用第三方接口，有时抛出异常
     */
    private static String simulateApiCallWithException() throws TimeoutException, IOException {
        int randomValue = RANDOM.nextInt(10);
        LOGGER.info("模拟API调用，随机值: {}", randomValue);
        
        // 模拟不同类型的异常
        if (randomValue < 5) {
            LOGGER.info("API调用超时，抛出TimeoutException");
            throw new TimeoutException("API调用超时");
        } else if (randomValue < 7) {
            LOGGER.info("API调用IO错误，抛出IOException");
            throw new IOException("API调用IO错误");
        } else {
            String result = "API调用成功，返回数据-" + randomValue;
            LOGGER.info(result);
            return result;
        }
    }
    
    /**
     * 模拟调用第三方接口，有时返回错误结果
     */
    private static String simulateApiCallWithErrorResult() {
        int randomValue = RANDOM.nextInt(10);
        LOGGER.info("模拟API调用，随机值: {}", randomValue);
        
        // 模拟返回错误结果的情况
        if (randomValue < 3) {
            LOGGER.info("API返回null");
            return null;
        } else if (randomValue < 7) {
            String errorResult = "error: 服务暂时不可用";
            LOGGER.info("API返回错误: {}", errorResult);
            return errorResult;
        } else {
            String result = "API调用成功，返回数据-" + randomValue;
            LOGGER.info(result);
            return result;
        }
    }
    
    /**
     * 模拟无返回值的方法，有时抛出异常
     */
    private static void simulateVoidMethodWithException() {
        int randomValue = RANDOM.nextInt(10);
        LOGGER.info("模拟无返回值方法调用，随机值: {}", randomValue);
        
        // 模拟抛出异常的情况
        if (randomValue < 7) {
            LOGGER.info("方法执行失败，抛出RuntimeException");
            throw new RuntimeException("方法执行失败");
        } else {
            LOGGER.info("方法执行成功");
        }
    }
}