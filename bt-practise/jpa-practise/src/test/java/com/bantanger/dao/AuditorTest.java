package com.bantanger.dao;

import com.bantanger.config.JpaAuditingConfiguration;
import com.bantanger.config.MyAuditorAware;
import com.bantanger.entity.qbe.SexEnum;
import com.bantanger.entity.qbe.User;
import com.bantanger.repository.qbe.UserRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;

import java.util.List;
import java.util.Optional;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/28
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Import(JpaAuditingConfiguration.class)
public class AuditorTest {
    @Resource
    private UserRepository userRepository;

    @MockBean
    MyAuditorAware myAuditorAware;

    @BeforeEach
//    @Rollback(false)
//    @Transactional
    public void testAuditing() throws JsonProcessingException {
        // 使用 mockito 模拟 web 环境，直接返回 13 这个用户 ID
        Mockito.when(myAuditorAware.getCurrentAuditor()).thenReturn(Optional.of(13L));
//        User user = User.builder()
//                .name("jack")
//                .email("123456@shark.com")
//                .age(20)
//                .sex(SexEnum.BOY)
//                .build();
//        userRepository.save(user);
//        List<User> users = userRepository.findAll();
//        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(users));
//        Assertions.assertEquals(13, users.get(0).getCreateUserId());
    }

    @Test
//    @Rollback(value = false)
//    @Transactional
    public void testCallBackUpdate() throws JsonProcessingException {
//        User user = userRepository.findByName("jack");
        User user = userRepository.getOne(1L);
        Assertions.assertEquals(1, user.getVersion());
        user.setSex(SexEnum.GIRL);
        userRepository.save(user);
        List<User> users = userRepository.findAll();
        users.forEach(u -> Assertions.assertEquals(200, u.getCreateUserId()));
        System.out.println(new ObjectMapper().writerWithDefaultPrettyPrinter().writeValueAsString(user));
    }


}
