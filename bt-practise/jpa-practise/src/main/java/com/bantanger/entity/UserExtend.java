package com.bantanger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/28
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserExtend { //用户扩展信息表
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private Long userId;
    private String idCard;
    private Integer ages;
    private String studentNumber;
}
