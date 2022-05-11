package com.example.backend.controller;

import com.example.backend.entity.Book;
import com.example.backend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/book")
public class BookController {
    @Autowired
    private BookRepository bookRepository;
    @CrossOrigin(origins = "http://jiarui.omen15:8000")
    @PostMapping(path = "/getBooks")
    public @ResponseBody List<Book> AddFootPrint(@RequestParam Integer number) {
        return bookRepository.getAll();
    }


}
