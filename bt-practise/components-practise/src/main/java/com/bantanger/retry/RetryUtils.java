package com.bantanger.retry;

import java.util.concurrent.TimeUnit;
import java.util.function.Predicate;
import java.util.function.Supplier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 重试工具类
 * 支持捕获指定异常或指定值后进行重试
 * 支持配置重试次数和重试间隔
 * 支持退避算法
 *
 * @author bantanger
 */
public class RetryUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(RetryUtils.class);

    /**
     * 默认重试次数
     */
    private static final int DEFAULT_RETRY_TIMES = 3;

    /**
     * 默认初始重试间隔（毫秒）
     */
    private static final long DEFAULT_INITIAL_INTERVAL = 1000;

    /**
     * 默认最大重试间隔（毫秒）
     */
    private static final long DEFAULT_MAX_INTERVAL = 10000;

    /**
     * 默认退避乘数
     */
    private static final double DEFAULT_MULTIPLIER = 1.5;

    /**
     * 使用默认配置执行带返回值的重试操作
     *
     * @param supplier 待执行的操作
     * @param <T>      返回值类型
     * @return 操作结果
     */
    public static <T> T executeWithRetry(Supplier<T> supplier) {
        return executeWithRetry(supplier, DEFAULT_RETRY_TIMES, DEFAULT_INITIAL_INTERVAL, DEFAULT_MAX_INTERVAL, DEFAULT_MULTIPLIER, null);
    }

    /**
     * 执行带返回值的重试操作，当返回值为null或满足失败条件时进行重试
     *
     * @param supplier        待执行的操作
     * @param maxRetryTimes   最大重试次数
     * @param initialInterval 初始重试间隔（毫秒）
     * @param maxInterval     最大重试间隔（毫秒）
     * @param multiplier      退避乘数
     * @param failurePredicate 失败条件判断，返回true表示需要重试
     * @param <T>             返回值类型
     * @return 操作结果
     */
    public static <T> T executeWithRetry(Supplier<T> supplier, int maxRetryTimes, long initialInterval, 
                                         long maxInterval, double multiplier, Predicate<T> failurePredicate) {
        int retryCount = 0;
        long interval = initialInterval;
        T result = null;
        Exception lastException = null;

        while (retryCount <= maxRetryTimes) {
            try {
                result = supplier.get();
                
                // 如果结果为null或满足失败条件，则进行重试
                if (result == null || (failurePredicate != null && failurePredicate.test(result))) {
                    if (retryCount == maxRetryTimes) {
                        // 达到最大重试次数，返回最后一次结果
                        return result;
                    }
                    
                    LOGGER.warn("需要重试，当前结果: {}, 重试次数: {}", result, retryCount);
                    sleep(interval);
                    interval = calculateNextInterval(interval, maxInterval, multiplier);
                    retryCount++;
                } else {
                    // 结果有效，直接返回
                    return result;
                }
            } catch (Exception e) {
                lastException = e;
                if (retryCount == maxRetryTimes) {
                    // 达到最大重试次数，抛出最后一次异常
                    throw new RuntimeException("重试" + maxRetryTimes + "次后失败", e);
                }
                
                LOGGER.warn("由于异常需要重试: {}, 重试次数: {}", e.getMessage(), retryCount, e);
                sleep(interval);
                interval = calculateNextInterval(interval, maxInterval, multiplier);
                retryCount++;
            }
        }

        // 如果有异常，抛出最后一次异常
        if (lastException != null) {
            throw new RuntimeException("重试" + maxRetryTimes + "次后失败", lastException);
        }
        
        return result;
    }

    /**
     * 执行带返回值的重试操作，捕获指定异常后进行重试
     *
     * @param supplier        待执行的操作
     * @param maxRetryTimes   最大重试次数
     * @param initialInterval 初始重试间隔（毫秒）
     * @param maxInterval     最大重试间隔（毫秒）
     * @param multiplier      退避乘数
     * @param retryForException 需要重试的异常类型
     * @param <T>             返回值类型
     * @return 操作结果
     */
    public static <T> T executeWithRetryForException(Supplier<T> supplier, int maxRetryTimes, long initialInterval, 
                                                    long maxInterval, double multiplier, Class<? extends Exception> retryForException) {
        int retryCount = 0;
        long interval = initialInterval;
        Exception lastException = null;

        while (retryCount <= maxRetryTimes) {
            try {
                return supplier.get();
            } catch (Exception e) {
                // 如果异常类型不匹配，直接抛出
                if (retryForException != null && !retryForException.isInstance(e)) {
                    throw new RuntimeException("异常类型不符合重试条件", e);
                }
                
                lastException = e;
                if (retryCount == maxRetryTimes) {
                    // 达到最大重试次数，抛出最后一次异常
                    throw new RuntimeException("重试" + maxRetryTimes + "次后失败", e);
                }
                
                LOGGER.warn("由于异常需要重试: {}, 重试次数: {}", e.getMessage(), retryCount, e);
                sleep(interval);
                interval = calculateNextInterval(interval, maxInterval, multiplier);
                retryCount++;
            }
        }

        // 如果有异常，抛出最后一次异常
        if (lastException != null) {
            throw new RuntimeException("重试" + maxRetryTimes + "次后失败", lastException);
        }
        
        return null;
    }

    /**
     * 执行无返回值的重试操作
     *
     * @param runnable        待执行的操作
     * @param maxRetryTimes   最大重试次数
     * @param initialInterval 初始重试间隔（毫秒）
     * @param maxInterval     最大重试间隔（毫秒）
     * @param multiplier      退避乘数
     */
    public static void executeWithRetry(Runnable runnable, int maxRetryTimes, long initialInterval, 
                                       long maxInterval, double multiplier) {
        int retryCount = 0;
        long interval = initialInterval;
        Exception lastException = null;

        while (retryCount <= maxRetryTimes) {
            try {
                runnable.run();
                return;
            } catch (Exception e) {
                lastException = e;
                if (retryCount == maxRetryTimes) {
                    // 达到最大重试次数，抛出最后一次异常
                    throw new RuntimeException("重试" + maxRetryTimes + "次后失败", e);
                }
                
                LOGGER.warn("由于异常需要重试: {}, 重试次数: {}", e.getMessage(), retryCount, e);
                sleep(interval);
                interval = calculateNextInterval(interval, maxInterval, multiplier);
                retryCount++;
            }
        }

        // 如果有异常，抛出最后一次异常
        if (lastException != null) {
            throw new RuntimeException("重试" + maxRetryTimes + "次后失败", lastException);
        }
    }

    /**
     * 使用默认配置执行无返回值的重试操作
     *
     * @param runnable 待执行的操作
     */
    public static void executeWithRetry(Runnable runnable) {
        executeWithRetry(runnable, DEFAULT_RETRY_TIMES, DEFAULT_INITIAL_INTERVAL, DEFAULT_MAX_INTERVAL, DEFAULT_MULTIPLIER);
    }

    /**
     * 计算下一次重试间隔，使用指数退避算法
     *
     * @param currentInterval 当前重试间隔
     * @param maxInterval     最大重试间隔
     * @param multiplier      退避乘数
     * @return 下一次重试间隔
     */
    private static long calculateNextInterval(long currentInterval, long maxInterval, double multiplier) {
        long nextInterval = (long) (currentInterval * multiplier);
        return Math.min(nextInterval, maxInterval);
    }

    /**
     * 线程休眠
     *
     * @param millis 休眠时间（毫秒）
     */
    private static void sleep(long millis) {
        try {
            TimeUnit.MILLISECONDS.sleep(millis);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException("线程休眠被中断", e);
        }
    }

    /**
     * 重试构建器，用于链式调用
     *
     * @param <T> 返回值类型
     */
    public static class RetryBuilder<T> {
        private Supplier<T> supplier;
        private Predicate<T> failurePredicate;
        private Class<? extends Exception> retryForException;
        private int maxRetryTimes = DEFAULT_RETRY_TIMES;
        private long initialInterval = DEFAULT_INITIAL_INTERVAL;
        private long maxInterval = DEFAULT_MAX_INTERVAL;
        private double multiplier = DEFAULT_MULTIPLIER;

        private RetryBuilder(Supplier<T> supplier) {
            this.supplier = supplier;
        }

        /**
         * 创建重试构建器
         *
         * @param supplier 待执行的操作
         * @param <T>      返回值类型
         * @return 重试构建器
         */
        public static <T> RetryBuilder<T> newBuilder(Supplier<T> supplier) {
            return new RetryBuilder<>(supplier);
        }

        /**
         * 设置失败条件判断
         *
         * @param failurePredicate 失败条件判断，返回true表示需要重试
         * @return 重试构建器
         */
        public RetryBuilder<T> retryIfResult(Predicate<T> failurePredicate) {
            this.failurePredicate = failurePredicate;
            return this;
        }

        /**
         * 设置需要重试的异常类型
         *
         * @param retryForException 需要重试的异常类型
         * @return 重试构建器
         */
        public RetryBuilder<T> retryOnException(Class<? extends Exception> retryForException) {
            this.retryForException = retryForException;
            return this;
        }

        /**
         * 设置最大重试次数
         *
         * @param maxRetryTimes 最大重试次数
         * @return 重试构建器
         */
        public RetryBuilder<T> withMaxRetryTimes(int maxRetryTimes) {
            this.maxRetryTimes = maxRetryTimes;
            return this;
        }

        /**
         * 设置初始重试间隔
         *
         * @param initialInterval 初始重试间隔（毫秒）
         * @return 重试构建器
         */
        public RetryBuilder<T> withInitialInterval(long initialInterval) {
            this.initialInterval = initialInterval;
            return this;
        }

        /**
         * 设置最大重试间隔
         *
         * @param maxInterval 最大重试间隔（毫秒）
         * @return 重试构建器
         */
        public RetryBuilder<T> withMaxInterval(long maxInterval) {
            this.maxInterval = maxInterval;
            return this;
        }

        /**
         * 设置退避乘数
         *
         * @param multiplier 退避乘数
         * @return 重试构建器
         */
        public RetryBuilder<T> withMultiplier(double multiplier) {
            this.multiplier = multiplier;
            return this;
        }

        /**
         * 执行重试操作
         *
         * @return 操作结果
         */
        public T execute() {
            if (retryForException != null) {
                return executeWithRetryForException(supplier, maxRetryTimes, initialInterval, maxInterval, multiplier, retryForException);
            } else {
                return executeWithRetry(supplier, maxRetryTimes, initialInterval, maxInterval, multiplier, failurePredicate);
            }
        }
    }
}