package com.bantanger.dao;

import com.bantanger.entity.User2;
import com.bantanger.entity.UserBase;
import com.bantanger.entity.UserExtend;
import com.bantanger.repository.UserBaseRepository;
import com.bantanger.repository.UserExtendRepository;
import com.bantanger.repository.User2Repository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;

import java.util.List;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/28
 */
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//@SpringBootTest
public class User2RepositoryTest {

    /**
     * <pre>
     * <a href="https://blog.csdn.net/datou123789/article/details/108655648">Failed to replace DataSource with an embedded database for tests Spring Data JPA</a>
     * </pre>
     */
    @Autowired
    private User2Repository user2Repository;
    @Autowired
    private UserBaseRepository userBaseRepository;
    @Autowired
    private UserExtendRepository userExtendRepository;
    private User2 user2;

//    @BeforeEach
    public void setUp() {
        user2 = user2Repository.save(User2.builder()
                .name("shark")
                .email("123456@qq.com")
                .firstName("chen")
                .lastName("songmin")
                .build());
        user2Repository.save(User2.builder().name("shark").email("shark@qq.com").firstName("chen").lastName("shark").build());
        user2Repository.save(User2.builder().name("bantang").email("shark1@qq.com").firstName("chen").lastName("bantang").build());
        user2Repository.save(User2.builder().name("chen").email("shark2@qq.com").firstName("chen").lastName("chan").build());
        user2Repository.save(User2.builder().name("chan").email("shark3@qq.com").firstName("chan").lastName("chan").build());
    }

    @Test
    public void test_saveUser() {
        Assertions.assertNotNull(user2);
        List<User2> user2s = user2Repository.findAll();
        System.out.println(user2s);
        Assertions.assertNotNull(user2s);
        user2Repository.flush();
    }

    @Test
    public void testProjections() {
        var ud = user2Repository.findByEmail("123456@qq.com");
        var un = user2Repository.findByName("shark");
//        List<Tuple> shark = user2Repository.findAllByEmailAndName("123456@qq.com", "shark");
//        System.out.println(shark);
        System.out.println(ud);
        System.out.println(un);
    }

    @Test
    public void testAtQuery() {
        var uea = user2Repository.findByEmailAddress("123456@qq.com");
        System.out.println(uea);

        var hen = user2Repository.findByFirstNameEndsWith("hen");
        System.out.println(hen);

        var chen = user2Repository.findByLastName("chan", PageRequest.of(0, 10));
        System.out.println("test1 = " + chen);

        var byEmail = user2Repository.findByEmail("123@126.com", PageRequest.of(0, 10));
        System.out.println(byEmail);
    }

    @Test
    public void testShowLinkDto() {
        //新增一条用户数据
        userBaseRepository.save(UserBase.builder().name("jack").email("123456@126.com").sex("man").address("shanghai").build());
        //再新增一条和用户一对一的UserExtend数据
        userExtendRepository.save(UserExtend.builder().userId(1L).idCard("shengfengzhenghao").ages(18).studentNumber("xuehao001").build());
        //查询我们想要的结果
        var dto = userBaseRepository.findByUserInfoId(1L);
        System.out.println(dto);
    }

    @Test
    public void testQueryAnnotationDto() {
        userBaseRepository.save(UserBase.builder().name("jack").email("123456@126.com").sex("man").address("shanghai").build());
        userExtendRepository.save(UserExtend.builder().userId(1L).idCard("shengfengzhenghao").ages(18).studentNumber("xuehao001").build());
        UserBaseRepository.UserSimpleDto userDto = userBaseRepository.findByUserSimpleDtoId(1L);
        System.out.println(userDto);
        System.out.println(userDto.getUserName()+":"+userDto.getEmail()+":"+userDto.getIdCard());
    }

    @Test
    public void test() {

    }
}
