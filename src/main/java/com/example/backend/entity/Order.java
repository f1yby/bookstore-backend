package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "orders")
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer oid;

    @ManyToOne
    @JsonIgnore
    private User user;
    @OneToMany
    @ToString.Exclude
    private List<OrderItem> orderItems;
}
