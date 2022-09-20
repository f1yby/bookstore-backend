package com.example.backend.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;
import java.util.Set;

@Getter
@Setter
@ToString
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
    private BigDecimal price;
    private String description;
    private Integer inventory;
    private String image;
    private boolean activated;

    @Override
    public boolean equals(Object o) {
        if (o == null || o.getClass() != getClass() || Hibernate.getClass(this) != Hibernate.getClass(o)) {
            return false;
        }
        if (this == o) return true;
        Book book = (Book) o;
        return bid != null && Objects.equals(bid, book.bid);
    }

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_bid")
    @ToString.Exclude
    @JsonIgnore
    private Set<OrderItem> orderItem;

    public Book() {

    }

    protected Book(Book book) {
        bid = book.bid;
        name = book.name;
        isbn = book.isbn;
        type = book.type;
        writers = book.writers;
        price = book.price;
        description = book.description;
        inventory = book.inventory;
        image = book.image;
        activated = book.activated;
        orderItem = book.orderItem;
    }

}
