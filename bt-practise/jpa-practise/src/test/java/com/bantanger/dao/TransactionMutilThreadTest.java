package com.bantanger.dao;

import com.bantanger.entity.UserInfo;
import com.bantanger.repository.UserInfoRepository;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionTemplate;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/31
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class TransactionMutilThreadTest extends AuditorTest {

    @Resource
    private UserInfoRepository userInfoRepository;

    @Resource
    private PlatformTransactionManager platformTransactionManager;

    @Test
    @Rollback(value = false)
//    @Transactional
    public void updateStudentNoTransactionTest() {
        List<UserInfo> all = userInfoRepository.findAll();
        all.forEach(x -> {
            x.setAges(x.getAges() + 1);
            userInfoRepository.save(x);
        });
//        System.out.println(all.size());
    }

    @Test
    @Rollback(value = false)
    public void updateStudentTransactionNoAutoTest() {
        List<UserInfo> all = userInfoRepository.findAll();
        TransactionTemplate transactionTemplate = new TransactionTemplate(platformTransactionManager);
        transactionTemplate.executeWithoutResult(status -> {
            all.forEach(x -> {
                x.setAges(x.getAges() + 1);
                userInfoRepository.save(x);
            });
        });
    }

    @Test
    public void updateStudentThreadsTest() {
        List<UserInfo> all = userInfoRepository.findAll();
        final int threadCount = 100;
        final int dataPartition = (all.size() + threadCount - 1) / threadCount;
        ExecutorService threadPool = Executors.newFixedThreadPool(threadCount);
        CountDownLatch countDownLatch = new CountDownLatch(threadCount);

        for (int i = 0; i < threadCount; i++) {
            List<UserInfo> threadDatas = all.stream()
                    .skip(i * dataPartition)
                    .limit(dataPartition)
                    .collect(Collectors.toList());
            threadPool.execute(() -> {
                updateStudent(threadDatas, countDownLatch);
            });
        }
        try {
            countDownLatch.await(30, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("  === ");
    }

    
    private void updateStudent(List<UserInfo> userInfoList, CountDownLatch countDownLatch) {
        TransactionTemplate transactionTemplate = new TransactionTemplate(platformTransactionManager);
        System.out.println("子线程：" + Thread.currentThread().getName());
        transactionTemplate.executeWithoutResult(status -> {
            userInfoList.forEach(x -> {
                x.setAges(x.getAges() + 1);
                userInfoRepository.save(x);
            });
            countDownLatch.countDown();
        });
    }

}
