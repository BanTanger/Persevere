package com.bantanger.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;

import java.io.Serializable;

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
@EqualsAndHashCode(callSuper = true)
//@ToString(exclude = "userBase")
public class UserInfo extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer ages;
    private String telephone;
    /**
     * user_info 表有 userId 作为关联关系的外键
     * 维护user的外键关联关系，配置一对一, 单项关联
     */
//    @MapsId // @MapsId 会将关联关系实体里配置了 @Id 注解的ID 注入到 @MapsId 标注的字段上
//    @OneToOne(cascade = {CascadeType.PERSIST})
//    @JoinColumn(foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT), name = "my_user_id")
//    @JsonIgnore
//    private UserBase userBase;
}