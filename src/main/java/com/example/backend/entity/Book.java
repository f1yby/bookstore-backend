package com.example.backend.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
@Table(name = "book")
@JsonIgnoreProperties(value = {"handler", "hibernateLazyInitializer", "fieldHandler"})
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer bid;


    private String isbn;
    private String name;
    private String type;
    private String writers;
    private Double price;
    private String description;
    private Integer inventory;
    private String image;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Book book = (Book) o;
        return bid != null && Objects.equals(bid, book.bid);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
