package com.bantanger.transaction.config;

import javax.annotation.Resource;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.concurrent.Callable;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/2
 */
@Configuration
public class TransactionExecuteManager {

    @Resource
    private PlatformTransactionManager transactionManager;

    private ThreadLocal<TransactionStatus> threadLocal = new ThreadLocal<>();

    public void begin() {
        DefaultTransactionDefinition defaultTransactionDefinition = new DefaultTransactionDefinition();
        threadLocal.set(transactionManager.getTransaction(defaultTransactionDefinition));
    }

    public void commit() {
        transactionManager.commit(threadLocal.get());
        threadLocal.remove();
    }

    public void rollback() {
        transactionManager.rollback(threadLocal.get());
        threadLocal.remove();
    }

    public <V> V execute(Callable<V> callable) {
        try {
            begin();
            V result = callable.call();
            commit();
            return result;
        } catch (Exception e) {
            rollback();
            throw new RuntimeException(e);
        }
    }
}
