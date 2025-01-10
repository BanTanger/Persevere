package com.bantanger.dao;

import com.bantanger.config.RetryConfiguration;
import com.bantanger.entity.UserInfo;
import com.bantanger.repository.UserInfoRepository;
import com.bantanger.service.UserInfoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/28
 */
@DataJpaTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ComponentScan(basePackageClasses = UserInfoService.class)
@Import(RetryConfiguration.class)
public class LockAndRetryTest extends AuditorTest {

    @Resource
    private UserInfoService userInfoService;
    @Resource
    private UserInfoRepository userInfoRepository;

    @Test
    public void testVersion() throws JsonProcessingException {
        UserInfo userInfo = userInfoRepository.save(UserInfo.builder().ages(20).telephone("123456").build());
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(userInfo));
        userInfoService.calculate(1L);
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(userInfo));
    }

    @Test
    @Rollback(false)
    @Transactional(propagation = Propagation.NEVER)
    public void testVersionException() {
//        userInfoRepository.save(UserInfo.builder().ages(20).telephone("123456").build());
        new Thread(() -> userInfoService.calculate(1L)).start(); // 多线程执行两次
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Exception exception = Assertions.assertThrows(ObjectOptimisticLockingFailureException.class,
                () -> userInfoService.calculate(1L));
        System.out.println(exception);
    }

    @Test
//    @Rollback(value = false)
//    @Transactional(propagation = Propagation.NEVER)
    public void testRetryable() {
        userInfoRepository.save(UserInfo.builder().ages(20).telephone("123456").build());
        new Thread(() -> userInfoService.calculate(1L)).start(); // 多线程执行两次
        try {
            Thread.sleep(10L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
//
//        Exception exception = Assertions.assertThrows(ObjectOptimisticLockingFailureException.class,
//                () -> userInfoService.calculate(1L));
//        System.out.println(exception);

        UserInfo calculate = userInfoService.calculate(1L);
        Assertions.assertEquals(25, calculate.getAges());
        Assertions.assertEquals(5, calculate.getVersion());
    }

}
