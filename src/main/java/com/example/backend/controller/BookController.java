package com.example.backend.controller;

import com.example.backend.dto.BookWithSell;
import com.example.backend.entity.Book;
import com.example.backend.service.BookService;
import org.apache.solr.client.solrj.SolrServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;


@RestController
@RequestMapping(path = "/book")
public class BookController {
    @Autowired
    private BookService bookService;


    @PostMapping(path = "/getBooks")
    public @ResponseBody Iterable<Book> getBooks() {
        return bookService.getBooks();
    }


    @PostMapping(path = "/getBooksByKeyword")
    public @ResponseBody Iterable<Book> getBooksByKeyword(@RequestParam String keyword) throws SolrServerException, IOException {
        return bookService.getBooksByKeyword(keyword);
    }

    @PostMapping(path = "/admin/update")
    public @ResponseBody String update(@RequestParam Integer bid, @RequestParam String name, @RequestParam String type, @RequestParam String writers, @RequestParam String isbn, @RequestParam String description, @RequestParam String image, @RequestParam Integer inventory, @RequestParam BigDecimal price, @RequestParam Boolean activated) {
        return bookService.update(bid, name, type, writers, isbn, description, image, inventory, price, activated);
    }


    @PostMapping(path = "/admin/add")
    public @ResponseBody String add(@RequestParam String name, @RequestParam String type, @RequestParam String writers, @RequestParam String isbn, @RequestParam String description, @RequestParam String image, @RequestParam Integer inventory, @RequestParam BigDecimal price, @RequestParam Boolean activated) {
        return bookService.add(name, type, writers, isbn, description, image, inventory, price, activated);
    }


    @PostMapping(path = "/admin/getBookWithSells")
    public @ResponseBody Iterable<BookWithSell> getBookWithSells() {
        return bookService.getBookWithSells();
    }


    @PostMapping(path = "/admin/getBookWithSellsByTimePeriod")
    public @ResponseBody Iterable<BookWithSell> getBookWithSellsByTimePeriod(@RequestParam String start, @RequestParam String end) {
        return bookService.getBookWithSellsByTimePeriod(Date.valueOf(start), Date.valueOf(end));
    }

    @PostMapping(path = "/admin/reloadIndex")
    public @ResponseBody String reloadIndex() {
        return bookService.reloadIndex();
    }


    @PostMapping(path = "/getBookWithSellsByUsernameAndPassword")
    public @ResponseBody Iterable<BookWithSell> getBookWithSellsByUsernameAndPassword(@RequestParam String username, @RequestParam String password) {
        return bookService.getBookWithSellsByUsernameAndPassword(username, password);
    }


    @PostMapping(path = "/getBookWithSellsByTimePeriodAndUsernameAndPassword")
    public @ResponseBody Iterable<BookWithSell> getBookWithSellsByTimePeriodAndUsernameAndPassword(@RequestParam String start, @RequestParam String end, @RequestParam String username, @RequestParam String password) {
        return bookService.getBookWithSellsByTimePeriodAndUsernameAndPassword(Date.valueOf(start), Date.valueOf(end), username, password);
    }

}
