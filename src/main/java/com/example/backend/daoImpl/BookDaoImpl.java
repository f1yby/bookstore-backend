package com.example.backend.daoImpl;

import com.example.backend.dao.BookDao;
import com.example.backend.entity.Book;
import com.example.backend.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
class BookDaoImpl implements BookDao {
    @Autowired
    BookRepository bookRepository;

    @Override
    public Iterable<Book> findAllByKeyword(String keyword) {
        return bookRepository.findAllByKeyword(keyword);
    }

    @Override
    public List<Book> findTop25ByOrderByBidAsc() {
        return bookRepository.findTop25ByOrderByBidAsc();
    }

    @Override
    public Optional<Book> findById(Integer bid) {
        return bookRepository.findById(bid);
    }
}