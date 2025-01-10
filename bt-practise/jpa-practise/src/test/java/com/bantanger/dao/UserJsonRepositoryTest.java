package com.bantanger.dao;

import com.bantanger.entity.UserJson;
import com.bantanger.repository.UserJsonRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.transaction.Transactional;
import org.assertj.core.util.Maps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Date;
import java.util.List;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/28
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UserJsonRepositoryTest {

    @Autowired
    private UserJsonRepository userJsonRepository;

    @BeforeEach
    @Rollback(false)
    @Transactional
    public void init() {
        UserJson user = UserJson.builder()
                .name("jackxx").createDate(new Date()).updateDate(new Date()).sex("men").email("123456@126.com").build();
        userJsonRepository.saveAndFlush(user);
    }

    /**
     * 测试用User关联关系操作
     *
     */
    @Test
    @Rollback(false)
    public void testUserJson() throws JsonProcessingException {
        UserJson userJson = userJsonRepository.findById(1L).get();
        userJson.setOther(Maps.newHashMap("address", "beijing"));
        ObjectMapper objectMapper = new ObjectMapper();
        // xuliehua 序列化
        var json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(userJson);
        System.out.println(json);

        // 反序列化
        UserJson user = objectMapper.readValue(json, UserJson.class);
        Object userJsons = objectMapper.readValue(json, new TypeReference<>() {
        });
        System.out.println(user);
        System.out.println(userJsons);
    }

}
