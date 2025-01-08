package com.bantanger.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.*;

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
public class Task {
    @Id
    @GeneratedValue
    private Long id;

    private String description;

    @ManyToOne
    private GroupUser groupUser;

    public GroupUser getUser() {
        return groupUser;
    }

    public void setUser(GroupUser groupUser) {
        this.groupUser = groupUser;
    }
}
