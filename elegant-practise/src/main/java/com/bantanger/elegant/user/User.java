package com.bantanger.elegant.user;

import com.bantanger.commons.annotation.FieldDesc;
import jakarta.persistence.*;
import lombok.Data;

/**
 * @author chensongmin
 * @description
 * @create 2025/1/5
 */
@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String username;
    private String address;
    @FieldDesc(name = "用户VIP等级类别")
    @Convert(converter = UserLevelTypeConverter.class)
    private UserLevelType userLevelType;
    @FieldDesc(name = "用户信用类别")
    @Convert(converter = UserCreditTypeConverter.class)
    private UserCreditType userCreditType;
}
