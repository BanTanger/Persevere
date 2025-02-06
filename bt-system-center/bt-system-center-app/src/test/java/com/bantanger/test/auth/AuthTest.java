package com.bantanger.test.auth;

import cn.hutool.crypto.digest.BCrypt;
import com.bantanger.domain.admin.creator.AdminUserCreator;
import com.bantanger.domain.admin.service.IAdminUserService;
import jakarta.annotation.Resource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author chensongmin
 * @description
 * @date 2025/2/6
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AuthTest {

    @Resource
    private IAdminUserService adminUserService;

    @Test
    public void testAddUser() {
        AdminUserCreator creator = new AdminUserCreator();
        creator.setUsername("半糖");
        creator.setPhone("13694998527");
        creator.setPassword(BCrypt.hashpw("123456"));
        adminUserService.createAdminUser(creator);
    }

    @Test
    public void testAddUser2() {
        AdminUserCreator creator = new AdminUserCreator();
        creator.setUsername("shark");
        creator.setPhone("1501477056");
        creator.setPassword(BCrypt.hashpw("123456"));
        creator.setDepartmentId(123456L);
        adminUserService.createAdminUser(creator);
    }

}
