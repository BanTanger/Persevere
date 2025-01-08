package com.bantanger.controller;

import com.bantanger.entity.UserInfo;
import com.bantanger.entity.UserJson;
import com.bantanger.repository.UserJsonRepository;
import jakarta.annotation.Resource;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.support.DomainClassConverter;
import org.springframework.data.web.config.EnableSpringDataWebSupport;
import org.springframework.data.web.config.SpringDataWebConfiguration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/28
 */
@RestController
@RequestMapping(path = "/api/v1")
public class UserController {

    @Resource
    private UserJsonRepository userJsonRepository;

    /**
     * <p>
     * 利用到 DomainClassConverter 的能力，是 SpringMVC 自定义 Formatter 的一种机制
     * <p/>
     * SpringBoot 通过自动装配, 服务启动时就具有 {@link SpringDataWebConfiguration},
     * 该类开启了 @{@link EnableSpringDataWebSupport} 注解，而 {@link SpringDataWebConfiguration} 是因为实现了
     * {@link WebMvcConfigurer} 的 {@link WebMvcConfigurer#addFormatters(FormatterRegistry)} 加载了所有自定义参数转化器的功能，
     * 所以才有了 {@link DomainClassConverter} 组件的支持
     * @param userInfo
     * @return
     */
    @GetMapping("/user/{id}")
    public UserInfo getUserInfoFromPath(@PathVariable("id") UserInfo userInfo) {
        return userInfo;
    }

    @GetMapping("/user")
    public UserInfo getUserInfoFromRequestParam(@RequestParam("id") UserInfo userInfo) {
        return userInfo;
    }

    @GetMapping("/users")
    public Page<UserJson> queryByPage(Pageable pageable, UserJson userJson) {
        return userJsonRepository.findAll(Example.of(userJson), pageable);
    }

    @GetMapping("/users/sort")
    public HttpEntity<List<UserJson>> queryBySort(Sort sort) {
        return new HttpEntity<>(userJsonRepository.findAll(sort));
    }

//    @PostMapping(path = "/user-single", consumes = { MediaType.APPLICATION_JSON_VALUE })
//    public User2 addNewUser(@RequestBody User2 user2) {
//        return repository.save(user2);
//    }
//
//    @GetMapping("/users")
//    @ResponseBody
//    public Page<User2> getAllUsers(Pageable request) {
//        return repository.findAll(request);
//    }
}
