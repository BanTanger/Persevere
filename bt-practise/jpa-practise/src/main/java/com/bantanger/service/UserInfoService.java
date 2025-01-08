package com.bantanger.service;

import com.bantanger.config.RetryConfiguration;
import com.bantanger.entity.UserInfo;
import com.bantanger.repository.UserInfoRepository;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/28
 */
@Service
public class UserInfoService {

    @Resource
    private UserInfoRepository userInfoRepository;

    /**
     * 重试，需要配合 @{@link EnableRetry} 开启，查看 {@link RetryConfiguration}
     * <br/>最大重试次数默认是 3
     * <pre>
     * 扩展使用
     * {@code
     * @Retryable(value = SQLException.class,
     *             maxAttemptsExpression = "${retry.maxAttempts}",
     *             backoff = @Backoff(delayExpression = "${retry.maxDelay}"))
     * }
     * </pre>
     * <pre>
     * 乐观锁 + 重试机制最佳实践
     * {@code
     * @Retryable(value = ObjectOptimisticLockingFailureException.class,
     *         maxAttemptsExpression = "${retry.maxAttempts}",
     *         backoff = @Backoff(delayExpression = "${retry.maxDelay}", multiplier = 1.5, random = true))
     * }
     * </pre>
     * @param userId
     * @return
     */
    @Retryable(value = ObjectOptimisticLockingFailureException.class,
        maxAttemptsExpression = "${retry.maxAttempts}",
        backoff = @Backoff(delayExpression = "${retry.maxDelay}", multiplier = 1.5, random = true))
    @Transactional
    public UserInfo calculate(Long userId) {
        UserInfo userInfo = userInfoRepository.getOne(userId);
        try {
            // 模拟计算耗时
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userInfo.setAges(userInfo.getAges() + 1);
        return userInfoRepository.saveAndFlush(userInfo);
    }

}
