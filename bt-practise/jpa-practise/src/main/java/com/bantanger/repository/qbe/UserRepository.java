package com.bantanger.repository.qbe;

import com.bantanger.entity.qbe.User;
import com.bantanger.entity.qbe.UserAddress;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/28
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByName(String name);
}
