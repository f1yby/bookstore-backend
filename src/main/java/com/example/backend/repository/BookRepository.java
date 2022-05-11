package com.example.backend.repository;

import com.example.backend.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Integer> {
    @Query("select b from Book b")
    List<Book> getAll();
}