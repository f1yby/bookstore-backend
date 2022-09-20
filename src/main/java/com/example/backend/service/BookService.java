package com.example.backend.service;

import com.example.backend.dto.BookWithSell;
import com.example.backend.entity.Book;

import java.math.BigDecimal;
import java.sql.Date;

public interface BookService {
    Iterable<Book> getBooks();

    Iterable<Book> getBooksByKeyword(String keyword);

    String update(Integer bid, String name, String type, String writers, String isbn, String description, String image, Integer inventory, BigDecimal price, Boolean activated);

    String add(String name, String type, String writers, String isbn, String description, String image, Integer inventory, BigDecimal price, Boolean activated);

    Iterable<BookWithSell> getBookWithSells();

    Iterable<BookWithSell> getBookWithSellsByTimePeriod(Date start, Date end);

    Iterable<BookWithSell> getBookWithSellsByUsernameAndPassword(String username, String password);

    Iterable<BookWithSell> getBookWithSellsByTimePeriodAndUsernameAndPassword(Date start, Date end, String username, String password);
}
