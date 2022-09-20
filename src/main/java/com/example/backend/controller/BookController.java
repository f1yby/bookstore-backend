package com.example.backend.controller;

import com.example.backend.dto.BookWithSell;
import com.example.backend.entity.Book;
import com.example.backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;


@RestController
@RequestMapping(path = "/book")
public class BookController {
    @Autowired
    private BookService bookService;

    @CrossOrigin(origins = "http://127.0.0.1:8000")
    @PostMapping(path = "/getBooks")
    public @ResponseBody Iterable<Book> getBooks() {
        return bookService.getBooks();
    }

    @CrossOrigin(origins = "http://127.0.0.1:8000")
    @PostMapping(path = "/getBooksByKeyword")
    public @ResponseBody Iterable<Book> getBooksByKeyword(@RequestParam String keyword) {
        return bookService.getBooksByKeyword(keyword);
    }

    @CrossOrigin(origins = "http://127.0.0.1:8000")
    @PostMapping(path = "/admin/update")
    public @ResponseBody String update(@RequestParam Integer bid, @RequestParam String name, @RequestParam String type, @RequestParam String writers, @RequestParam String isbn, @RequestParam String description, @RequestParam String image, @RequestParam Integer inventory, @RequestParam BigDecimal price, @RequestParam Boolean activated) {
        return bookService.update(bid, name, type, writers, isbn, description, image, inventory, price, activated);
    }


    @CrossOrigin(origins = "http://127.0.0.1:8000")
    @PostMapping(path = "/admin/add")
    public @ResponseBody String add(@RequestParam String name, @RequestParam String type, @RequestParam String writers, @RequestParam String isbn, @RequestParam String description, @RequestParam String image, @RequestParam Integer inventory, @RequestParam BigDecimal price, @RequestParam Boolean activated) {
        return bookService.add(name, type, writers, isbn, description, image, inventory, price, activated);
    }

    @CrossOrigin(origins = "http://127.0.0.1:8000")
    @PostMapping(path = "/admin/getBookWithSells")
    public @ResponseBody Iterable<BookWithSell> getBookWithSells() {
        return bookService.getBookWithSells();
    }

    @CrossOrigin(origins = "http://127.0.0.1:8000")
    @PostMapping(path = "/admin/getBookWithSellsByTimePeriod")
    public @ResponseBody Iterable<BookWithSell> getBookWithSellsByTimePeriod(@RequestParam String start, @RequestParam String end) {
        return bookService.getBookWithSellsByTimePeriod(Date.valueOf(start), Date.valueOf(end));
    }

    @CrossOrigin(origins = "http://127.0.0.1:8000")
    @PostMapping(path = "/getBookWithSellsByUsernameAndPassword")
    public @ResponseBody Iterable<BookWithSell> getBookWithSellsByUsernameAndPassword(@RequestParam String username, @RequestParam String password) {
        return bookService.getBookWithSellsByUsernameAndPassword(username, password);
    }

    @CrossOrigin(origins = "http://127.0.0.1:8000")
    @PostMapping(path = "/getBookWithSellsByTimePeriodAndUsernameAndPassword")
    public @ResponseBody Iterable<BookWithSell> getBookWithSellsByTimePeriodAndUsernameAndPassword(@RequestParam String start, @RequestParam String end, @RequestParam String username, @RequestParam String password) {
        return bookService.getBookWithSellsByTimePeriodAndUsernameAndPassword(Date.valueOf(start), Date.valueOf(end), username, password);
    }

}
