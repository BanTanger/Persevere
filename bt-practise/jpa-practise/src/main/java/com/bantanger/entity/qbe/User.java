package com.bantanger.entity.qbe;

import com.bantanger.entity.BaseEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

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
@Table(name = "my_user")
@ToString(exclude = "address")
public class User extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String email;
    @Enumerated(EnumType.STRING)
    private SexEnum sex;
    private Integer age;
    @OneToMany(mappedBy = "user")
    @JsonBackReference
    private List<UserAddress> address;

    @PrePersist
    private void prePersist() {
        System.out.println("prePersist::" + this);
//        this.setVersion(1);
    }

    @PostPersist
    public void postPersist() {
        System.out.println("postPersist::" + this);
    }
}
