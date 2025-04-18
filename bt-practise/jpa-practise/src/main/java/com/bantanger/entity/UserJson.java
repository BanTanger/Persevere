package com.bantanger.entity;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author chensongmin
 * @description
 * @create 2024/12/28
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonPropertyOrder({"createDate", "email"})
public class UserJson {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @JsonProperty("my_name")
    private String name;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date createDate;
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm")
    private Date updateDate;
    private String email;
    @JsonIgnore
    private String sex;
    @JsonCreator
    public UserJson(@JsonProperty("email") String email) {
        System.out.println("其他业务处理");
        this.email = email;
    }
    @Transient
    @JsonAnySetter
    private Map<String, Object> other = new HashMap<>();
    @JsonAnyGetter
    public Map<String, Object> getOther() {
        return other;
    }
}
