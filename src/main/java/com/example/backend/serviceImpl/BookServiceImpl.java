package com.example.backend.serviceImpl;

import com.example.backend.dao.BookDao;
import com.example.backend.entity.Book;
import com.example.backend.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;


    public  Iterable<Book> getBooks() {
        return bookDao.findTop25ByOrderByBidAsc();
    }


    public Iterable<Book> getBooksByKeyword( String keyword) {
        return bookDao.findAllByKeyword(keyword);
    }
}
