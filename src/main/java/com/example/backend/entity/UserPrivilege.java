package com.example.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Optional;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "user_privilege")
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
public class UserPrivilege {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer upid;

    private String privilege;



}
