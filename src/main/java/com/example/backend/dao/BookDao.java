package com.example.backend.dao;

import com.example.backend.entity.Book;

import java.util.Optional;

public interface BookDao {
    Iterable<Book> findAllByKeyword(String keyword);

    Iterable<Book> findAll();

    Optional<Book> findById(Integer bid);
    void save(Book book);
}