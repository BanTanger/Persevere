package com.bantanger.elegant.service.facade.impl;

import com.bantanger.elegant.service.facade.IUserFacadeService;
import com.bantanger.elegant.user.User;
import com.bantanger.elegant.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/5
 */
@Service
@RequiredArgsConstructor
public class UserFacadeServiceImpl implements IUserFacadeService {

    private final UserRepository userRepository;

    @Override
    public Optional<User> findUserInfoById(Long userId) {
        return userRepository.findById(userId);
    }

}
