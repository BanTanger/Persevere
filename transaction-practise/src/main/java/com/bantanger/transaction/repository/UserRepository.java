package com.bantanger.transaction.repository;

import com.bantanger.transaction.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/2
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {
}
