package com.bantanger.transaction.service;

import com.bantanger.TransactionApplication;
import com.bantanger.transaction.entity.UserEntity;
import com.bantanger.transaction.repository.UserRepository;
import javax.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/2
 */
@SpringBootTest(classes = {TransactionApplication.class})
class IUserServiceTest {

    @Resource
    private IUserService userService;
    @Resource
    private UserRepository userRepository;

    @Test
    void save() {
        for (int i = 0; i < 100000; i++) {
            userService.save("bantanger", "123456");
        }
    }

    @Test
    void saveThrowException() {
        userService.saveTemplate("bantanger", "123456");
    }

    @Test
    void saveWithThread() {
        System.out.println("start = " + (long) userRepository.findAll().size());
        List<UserEntity> all = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            UserEntity user = UserEntity.builder().username("bantanger" + i).password("password").build();
            all.add(user);
        }
        userService.saveWithThreadBatch(all);
        System.out.println("end = " + (long) userRepository.findAll().size());

    }

}