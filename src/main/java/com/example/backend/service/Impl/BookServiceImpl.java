package com.example.backend.service.Impl;

import com.example.backend.dao.BookDao;
import com.example.backend.dto.BookWithSell;
import com.example.backend.entity.Book;
import com.example.backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.LinkedList;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;


    public Iterable<Book> getBooks() {
        return bookDao.findAll();
    }


    public Iterable<Book> getBooksByKeyword(String keyword) {
        return bookDao.findAllByKeyword(keyword);
    }

    @Override
    public String update(Integer bid, String name, String type, String writers, String isbn, String description, String image, Integer inventory, BigDecimal price, Boolean activated) {
        Book book = new Book();
        book.setBid(bid);
        return setBookData(name, type, writers, isbn, description, image, inventory, price, activated, book);
    }

    @Override
    public String add(String name, String type, String writers, String isbn, String description, String image, Integer inventory, BigDecimal price, Boolean activated) {
        Book book = new Book();
        return setBookData(name, type, writers, isbn, description, image, inventory, price, activated, book);
    }

    @Override
    public Iterable<BookWithSell> getBookWithSells() {
        LinkedList<BookWithSell> bookWithSells = new LinkedList<>();
        bookDao.findAll().forEach(book -> bookWithSells.add(new BookWithSell(book)));
        return bookWithSells;
    }

    @Override
    public Iterable<BookWithSell> getBookWithSellsByTimePeriod(Date start, Date end) {
        LinkedList<BookWithSell> bookWithSells = new LinkedList<>();
        bookDao.findAll().forEach(book -> bookWithSells.add(new BookWithSell(book, start, end)));
        return bookWithSells;
    }

    @Override
    public Iterable<BookWithSell> getBookWithSellsByUsernameAndPassword(String username, String password) {
        LinkedList<BookWithSell> bookWithSells = new LinkedList<>();
        bookDao.findAll().forEach(book -> bookWithSells.add(new BookWithSell(book, username, password)));
        return bookWithSells;
    }

    @Override
    public Iterable<BookWithSell> getBookWithSellsByTimePeriodAndUsernameAndPassword(Date start, Date end, String username, String password) {
        LinkedList<BookWithSell> bookWithSells = new LinkedList<>();
        bookDao.findAll().forEach(book -> bookWithSells.add(new BookWithSell(book, start, end, username, password)));
        return bookWithSells;
    }

    private String setBookData(String name, String type, String writers, String isbn, String description, String image, Integer inventory, BigDecimal price, Boolean activated, Book book) {
        book.setDescription(description);
        book.setImage(image);
        book.setName(name);
        book.setInventory(inventory);
        book.setIsbn(isbn);
        book.setType(type);
        book.setWriters(writers);
        book.setPrice(price);
        book.setActivated(activated);
        bookDao.save(book);
        return "Ok";
    }
}
