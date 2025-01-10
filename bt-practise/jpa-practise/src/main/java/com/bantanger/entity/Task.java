package com.bantanger.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
