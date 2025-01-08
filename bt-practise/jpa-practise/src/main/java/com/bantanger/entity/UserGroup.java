package com.bantanger.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/27
 */
@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class UserGroup {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<GroupUser> groupUsers = new HashSet<>();

    public UserGroup(String name, Set<GroupUser> groupUsers) {
        this.name = name;
        this.groupUsers = groupUsers;
    }
}
