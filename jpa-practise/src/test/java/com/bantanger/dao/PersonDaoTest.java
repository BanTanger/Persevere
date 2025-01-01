package com.bantanger.dao;

import com.bantanger.entity.Publishers;
import com.bantanger.service.PublisherService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/27
 */
@SpringBootTest
public class PersonDaoTest {

    @Resource
    private PublisherService service;

    @Test
    public void publishersRepository_test() {
//        List<Publishers> list = Arrays.asList(new Publishers("bantanger", "guangdong", 20), new Publishers("chensongmin", "guangdong", 50));
//        for (Publishers publishers : list) {
//            service.save(publishers);
//        }
//        System.out.println(service.findAll());
        List<Publishers> result = service.findPublishersWithMinJournalsInLocation(30, "guangdong");
        System.out.println(result);
    }

}