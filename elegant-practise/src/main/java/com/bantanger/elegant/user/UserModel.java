package com.bantanger.elegant.user;

import com.bantanger.elegant.user.UserCreditType;
import com.bantanger.elegant.user.UserLevelType;
import lombok.*;

/**
 * @author chensongmin
 * @description 用户编程上下文
 * @create 2025/1/5
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserModel {

    private Long userId;
    private String username;
    private String address;
    private UserLevelType userLevelType;
    private UserCreditType userCreditType;

}
