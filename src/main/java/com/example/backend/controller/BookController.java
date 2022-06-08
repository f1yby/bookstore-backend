package com.example.backend.controller;

import com.example.backend.entity.Book;
import com.example.backend.repository.BookRepository;
import com.example.backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @CrossOrigin(origins = "http://jiarui.omen15:8000")
    @PostMapping(path = "/getBooks")
    public @ResponseBody Iterable<Book> getBooks() {
        return bookService.getBooks();
    }

    @CrossOrigin(origins = "http://jiarui.omen15:8000")
    @PostMapping(path = "/getBooksByKeyword")
    public @ResponseBody Iterable<Book> getBooksByKeyword(@RequestParam String keyword) {
        return bookService.getBooksByKeyword(keyword);
    }
}
