package com.example.backend.dao;

import com.example.backend.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {
    Iterable<Book> findAllByKeyword(String keyword);

    List<Book> findTop25ByOrderByBidAsc();

    Optional<Book> findById(Integer bid);
}