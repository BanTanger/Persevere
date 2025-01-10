package com.bantanger.entity;

import javax.persistence.*;
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
public class UserBase {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    @Version
    private Long version;
    private String sex;
    private String address;

    /**
     * 双向关联, mappedBy 指的是关联关系的另一方的属性字段名称，而不是数据库字段，也不是当前实体的对象名称
     */
    @OneToOne
    private UserInfo userInfo;
}
