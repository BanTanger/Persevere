package com.bantanger.elegant.user.repository;

import com.bantanger.elegant.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/5
 */
public interface UserRepository extends JpaRepository<User, Long> {
}
