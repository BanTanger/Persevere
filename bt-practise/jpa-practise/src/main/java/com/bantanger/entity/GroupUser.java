package com.bantanger.entity;

import javax.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/27
 */
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GroupUser {
    @Id
    @GeneratedValue
    private Long id;

    private String login;

    @ManyToMany(mappedBy = "groupUsers", cascade = CascadeType.PERSIST)
    private Set<UserGroup> userGroups = new HashSet<>();

    @OneToMany(cascade = CascadeType.PERSIST, mappedBy = "groupUser")
    private Set<Task> tasks = new HashSet<>(0);
}
