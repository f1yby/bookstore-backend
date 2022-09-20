package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user")
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer uid;
    private String username;
    private String email;
    private String password;
    private String permission;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @ToString.Exclude
    @JsonIgnore
    @JoinColumn(name = "user_uid")
    private Set<OrderItem> orderItems;

    public User() {

    }

    protected User(User user) {
        uid = user.uid;
        username = user.username;
        email = user.email;
        password = user.password;
        permission = user.permission;
        orderItems = user.orderItems;
    }

}
