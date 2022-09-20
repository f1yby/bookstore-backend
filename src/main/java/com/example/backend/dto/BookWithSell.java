package com.example.backend.dto;

import com.example.backend.entity.Book;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Objects;

@Getter
@Setter
public class BookWithSell extends Book {
    Integer sell;

    public BookWithSell(Book book) {
        super(book);
        sell = 0;
        book.getOrderItem().forEach(orderItem -> {
            if (!orderItem.isActivated()) {
                sell += orderItem.getCount();
            }
        });
    }


    public BookWithSell(Book book, Date start, Date end) {
        super(book);
        sell = 0;
        book.getOrderItem().forEach(orderItem -> {
            if (!orderItem.isActivated() && orderItem.getOrder().getDate().before(end) && !orderItem.getOrder().getDate().before(start)) {
                sell += orderItem.getCount();
            }
        });
    }

    public BookWithSell(Book book, String username, String password) {
        super(book);
        sell = 0;
        book.getOrderItem().forEach(orderItem -> {
            if (!orderItem.isActivated() && Objects.equals(orderItem.getUser().getUsername(), username) && Objects.equals(orderItem.getUser().getPassword(), password)) {
                sell += orderItem.getCount();
            }
        });
    }


    public BookWithSell(Book book, Date start, Date end, String username, String password) {
        super(book);
        sell = 0;
        book.getOrderItem().forEach(orderItem -> {
            if (!orderItem.isActivated() && orderItem.getOrder().getDate().before(end) && !orderItem.getOrder().getDate().before(start)&& Objects.equals(orderItem.getUser().getUsername(), username) && Objects.equals(orderItem.getUser().getPassword(), password)) {
                sell += orderItem.getCount();
            }
        });
    }


}
