package com.bantanger.repository;

import com.bantanger.entity.UserGroup;
import org.springframework.data.repository.Repository;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/27
 */
public interface UserGroupRepository extends Repository<UserGroup, Long> {

    UserGroup save(UserGroup userGroup);

}
