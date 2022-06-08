package com.example.backend.service;

import com.example.backend.entity.Book;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

public interface BookService {
    Iterable<Book> getBooks();

    Iterable<Book> getBooksByKeyword(String keyword);
}
