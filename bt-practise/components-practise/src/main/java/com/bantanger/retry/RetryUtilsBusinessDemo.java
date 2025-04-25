package com.bantanger.retry;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeoutException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * RetryUtils在实际业务场景中的应用示例
 * 
 * @author bantanger
 */
public class RetryUtilsBusinessDemo {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(RetryUtilsBusinessDemo.class);
    
    /**
     * 模拟第三方API客户端
     */
    private static class ThirdPartyApiClient {
        
        /**
         * 模拟调用第三方接口获取数据
         * 可能返回null或抛出异常
         */
        public Map<String, Object> fetchData(String requestId) throws Exception {
            // 模拟接口调用不稳定的情况
            double random = Math.random();
            
            if (random < 0.4) {
                // 模拟超时异常
                LOGGER.warn("接口调用超时，requestId: {}", requestId);
                throw new TimeoutException("接口调用超时");
            } else if (random < 0.6) {
                // 模拟返回null
                LOGGER.warn("接口返回null，requestId: {}", requestId);
                return null;
            } else {
                // 模拟正常返回数据
                Map<String, Object> result = new HashMap<>();
                result.put("requestId", requestId);
                result.put("data", "这是接口返回的数据");
                result.put("timestamp", System.currentTimeMillis());
                LOGGER.info("接口调用成功，requestId: {}", requestId);
                return result;
            }
        }
    }
    
    /**
     * 业务服务类
     */
    public static class BusinessService {
        private final ThirdPartyApiClient apiClient = new ThirdPartyApiClient();
        
        /**
         * 不使用重试机制的方法
         */
        public Map<String, Object> fetchDataWithoutRetry(String requestId) {
            try {
                return apiClient.fetchData(requestId);
            } catch (Exception e) {
                LOGGER.error("获取数据失败: {}", e.getMessage(), e);
                return null;
            }
        }
        
        /**
         * 使用重试机制的方法 - 基本用法
         */
        public Map<String, Object> fetchDataWithRetry(String requestId) {
            return RetryUtils.executeWithRetry(() -> {
                try {
                    return apiClient.fetchData(requestId);
                } catch (Exception e) {
                    LOGGER.warn("获取数据失败，准备重试: {}", e.getMessage());
                    throw new RuntimeException(e);
                }
            });
        }
        
        /**
         * 使用重试机制的方法 - 高级用法
         */
        public Map<String, Object> fetchDataWithAdvancedRetry(String requestId) {
            return RetryUtils.RetryBuilder.newBuilder(() -> {
                try {
                    return apiClient.fetchData(requestId);
                } catch (Exception e) {
                    LOGGER.warn("获取数据失败，准备重试: {}", e.getMessage());
                    throw new RuntimeException(e);
                }
            })
            .retryIfResult(Objects::isNull) // 当结果为null时重试
            .retryOnException(RuntimeException.class) // 当发生RuntimeException时重试
            .withMaxRetryTimes(5) // 最多重试5次
            .withInitialInterval(1000) // 初始重试间隔1秒
            .withMaxInterval(10000) // 最大重试间隔10秒
            .withMultiplier(2.0) // 退避乘数为2
            .execute();
        }
    }
    
    public static void main(String[] args) {
        BusinessService service = new BusinessService();
        String requestId = "REQ-" + System.currentTimeMillis();
        
        // 1. 不使用重试机制
        LOGGER.info("=== 不使用重试机制 ===");
        Map<String, Object> result1 = service.fetchDataWithoutRetry(requestId);
        LOGGER.info("结果: {}", result1);
        
        // 2. 使用基本重试机制
        LOGGER.info("=== 使用基本重试机制 ===");
        Map<String, Object> result2 = service.fetchDataWithRetry(requestId);
        LOGGER.info("结果: {}", result2);
        
        // 3. 使用高级重试机制
        LOGGER.info("=== 使用高级重试机制 ===");
        Map<String, Object> result3 = service.fetchDataWithAdvancedRetry(requestId);
        LOGGER.info("结果: {}", result3);
    }
}