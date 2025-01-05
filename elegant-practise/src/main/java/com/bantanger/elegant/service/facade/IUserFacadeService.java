package com.bantanger.elegant.service.facade;

import com.bantanger.elegant.user.User;

import java.util.Optional;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/5
 */
public interface IUserFacadeService {

    Optional<User> findUserInfoById(Long userId);

}
