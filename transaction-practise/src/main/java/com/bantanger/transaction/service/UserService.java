package com.bantanger.transaction.service;

import com.bantanger.transaction.config.TransactionExecuteManager;
import com.bantanger.transaction.entity.UserEntity;
import com.bantanger.transaction.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/2
 */

interface IUserService {
    void save(String username, String password);

    void saveTemplate(String username, String password);

    void saveWithThreadBatch(List<UserEntity> all);
}

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository repository;
    private final TransactionExecuteManager transactionExecuteManager;

    @Override
    public void save(String username, String password) {
        UserEntity user = UserEntity.builder().username(username).password(password).build();
        transactionExecuteManager.execute(() -> repository.save(user));
    }

    @Override
    public void saveTemplate(String username, String password) {
        UserEntity user = UserEntity.builder().username(username).password(password).build();
        transactionExecuteManager.begin();
        try {
            repository.save(user);
            int i = 1 / 0;
            transactionExecuteManager.commit();
        } catch (Exception e) {
            transactionExecuteManager.rollback();
            throw new RuntimeException(e);
        }
    }

    @Override
    public void saveWithThreadBatch(List<UserEntity> all) {
        try {
            transactionExecuteManager.begin();
            List<List<UserEntity>> lists = averageAssign(all, 5);
            ExecutorService threadPool = Executors.newFixedThreadPool(lists.size());
            Thread[] threads = new Thread[lists.size()];

            // 监控子线程执行完毕,再执行主线程,要不然会导致主线程关闭,子线程也会随着关闭
            CountDownLatch countDownLatch = new CountDownLatch(lists.size());
            AtomicBoolean atomicBoolean = new AtomicBoolean(true);

            for (int i = 0; i < lists.size(); i++) {
                // 最后一个抛异常，模拟是否会回滚
                if (lists.size() - 1 == i) {
                    atomicBoolean.set(false);
                }
                List<UserEntity> list = lists.get(i);
                threads[i] = new Thread(() -> {
                    try {
                        if (!atomicBoolean.get()) {
                            throw new RuntimeException("模拟异常");
                        }
                        repository.saveAll(list);
                    } catch (Exception e) {
                        transactionExecuteManager.rollback();
                    } finally {
                        countDownLatch.countDown();
                    }
                });
            }
            for (int i = 0; i <lists.size(); i++){
                threadPool.execute(threads[i]);
            }
            // 当子线程执行完毕时,主线程再往下执行
            countDownLatch.await();
            transactionExecuteManager.commit();
        } catch (InterruptedException e) {
            transactionExecuteManager.rollback();
            throw new RuntimeException(e);
        }

        System.out.println("添加完毕");
    }

    private static <T> List<List<T>> averageAssign(List<T> list, int n) {
        List<List<T>> result = new ArrayList<List<T>>();
        int remaider = list.size() % n;
        int number = list.size() / n;
        int offset = 0; // 偏移量
        for (int i = 0; i < n; i++) {
            List<T> value = null;
            if (remaider > 0) {
                value = list.subList(i * number + offset, (i + 1) * number + offset + 1);
                remaider --;
                offset ++;
            } else {
                value = list.subList(i * number + offset, (i + 1) * number + offset);
            }
            result.add(value);
        }
        return result;
    }
}