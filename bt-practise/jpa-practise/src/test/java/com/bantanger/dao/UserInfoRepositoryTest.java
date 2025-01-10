package com.bantanger.dao;

import com.bantanger.entity.UserBase;
import com.bantanger.entity.UserInfo;
import com.bantanger.repository.UserBaseRepository;
import com.bantanger.repository.UserInfoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import javax.annotation.Resource;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/28
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserInfoRepositoryTest extends AuditorTest {

    @Resource
    private UserInfoRepository userInfoRepository;
    @Resource
    private UserBaseRepository userBaseRepository;

    @Test
    @Rollback(false)
    @Transactional
    public void testUserRelationships() throws JsonProcessingException {
//        UserBase user = UserBase.builder().name("jackxx").email("123456@126.com").build();
        //保存userInfo的同上也会保存User信息
        for (int i = 0; i < 100000; i++) {
            UserInfo userInfo = UserInfo.builder().ages(12).telephone("12345678" + i).build();
            userInfoRepository.save(userInfo);
        }
//        //删除userInfo，同时也会级联的删除user记录
//        userInfoRepository.delete(userInfo);
    }

//    @BeforeEach
//    @Rollback(value = false)
//    @Transactional
//    void setUp() {
//        UserBase user = UserBase.builder().name("jackxx").email("123456@126.com").build();
//        UserInfo userInfo = UserInfo.builder().ages(12).userBase(user).telephone("12345678").build();
//        //保存userInfo的同上也会保存User信息
//        userInfoRepository.saveAndFlush(userInfo);
//    }

//    @Test
//    @Rollback(value = false)
//    public void testUserRelationships2() {
//        UserInfo userInfo1 = userInfoRepository.getOne(1L);
//        System.out.println(userInfo1);
//        System.out.println(userInfo1.getUserBase().getId());
//    }

}
