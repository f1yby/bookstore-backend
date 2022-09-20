package com.example.backend.repository;

import com.example.backend.entity.Book;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface BookRepository extends CrudRepository<Book, Integer> {
    @Query("SELECT b FROM Book b WHERE b.name LIKE concat('%', :keyword, '%') or b.writers LIKE concat('%', :keyword, '%') or b.description LIKE concat('%', :keyword, '%')")
    Iterable<Book> findAllByKeyword(String keyword);

}