package com.bantanger.dao;

import com.bantanger.entity.qbe.SexEnum;
import com.bantanger.entity.qbe.User;
import com.bantanger.entity.qbe.UserAddress;
import com.bantanger.repository.qbe.UserAddressRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import jakarta.transaction.Transactional;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;

import java.time.Instant;
import java.util.Date;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/28
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserAddressRepositoryTest {

    @Resource
    private UserAddressRepository userAddressRepository;
    private Date now = new Date();

    @BeforeEach
    @Rollback(value = false)
    @Transactional
    public void init() {
        User user = User.builder()
                .name("bantanger")
                .email("sharksharkchan@outlook.com")
                .sex(SexEnum.BOY)
//                .createDate(now)
//                .updateDate(now)
                .age(20)
                .build();
        userAddressRepository.saveAll(Lists.newArrayList(UserAddress.builder().user(user).address("shanghai").build(),
                UserAddress.builder().user(user).address("beijing").build()));
    }

    @Test
    @Rollback(value = false)
    public void testQBEFromUserAddress() throws JsonProcessingException {
        // 模拟前端传入的序列化请求体
        User request = User.builder().name("bantanger").age(20).email("sharksharkchan@outlook.com").build();
        UserAddress address = UserAddress.builder().address("bei").user(request).build();
        ObjectMapper objectMapper = new ObjectMapper();
        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(address);
        System.out.println(json);

        ExampleMatcher em = ExampleMatcher.matching()
                .withMatcher("user.email", ExampleMatcher.GenericPropertyMatchers.startsWith())
                .withMatcher("address", ExampleMatcher.GenericPropertyMatchers.startsWith());
        Page<UserAddress> u = userAddressRepository.findAll(Example.of(address, em), PageRequest.of(0, 2));
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(u));
    }

}
