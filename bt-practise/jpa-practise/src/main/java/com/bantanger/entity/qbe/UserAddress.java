package com.bantanger.entity.qbe;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.*;

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
//@ToString(exclude = "user")
public class UserAddress {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String address;
    @ManyToOne(cascade = CascadeType.ALL)
//    @JsonIgnore
    @JsonBackReference
    private User user;
}
