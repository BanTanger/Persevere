package com.bantanger.repository;

import com.bantanger.entity.UserJson;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/28
 */
public interface UserJsonRepository extends JpaRepository<UserJson, Long> {
}
