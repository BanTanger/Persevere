package com.bantanger.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PostLoad;
import javax.persistence.PostRemove;
import javax.persistence.PostUpdate;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/28
 */
@Data
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //    @CreatedBy
    private Long createUserId;
    @CreatedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date createTime;
    @LastModifiedBy
    private Long lastModifiedUserId;
    @LastModifiedDate
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date lastModifiedTime;
    @Version
    private Integer version;
    private Boolean deleted;

    @PreUpdate
    public void preUpdate() {
//        System.out.println("preUpdate::" + this);
        this.setCreateUserId(200L);
    }

    @PostUpdate
    public void postUpdate() {
//        System.out.println("postUpdate::" + this);
    }

    @PreRemove
    public void preRemove() {
//        System.out.println("preRemove::" + this);
    }

    @PostRemove
    public void postRemove() {
//        System.out.println("postRemove::" + this);
    }

    @PostLoad
    public void postLoad() {
//        System.out.println("postLoad::" + this);
    }

}
