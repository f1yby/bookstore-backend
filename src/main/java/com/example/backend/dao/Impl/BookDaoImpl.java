package com.example.backend.dao.Impl;

import com.alibaba.fastjson.JSONArray;
import com.example.backend.dao.BookDao;
import com.example.backend.entity.Book;
import com.example.backend.repository.BookRepository;
import com.example.backend.util.RedisUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
class BookDaoImpl implements BookDao {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookDao.class);
    @Autowired
    BookRepository bookRepository;

    @Autowired
    RedisUtil redisUtil;

    @Override
    public Iterable<Book> findAllByKeyword(String keyword) {
        return bookRepository.findAllByKeyword(keyword);
    }

    @Override
    public Iterable<Book> findAll() {
        return bookRepository.findAll();
    }


    @Override
    public Optional<Book> findById(Integer bid) {
        Book book = null;
        Object b = redisUtil.get("book" + bid);
        LOGGER.info("search book " + bid);
        if (b == null) {
            LOGGER.info(bid + " is not in cache");

            Optional<Book> optionalBook = bookRepository.findById(bid);
            if (optionalBook.isPresent()) {
                LOGGER.info(bid + " is in db");
                ObjectMapper objectMapper = new ObjectMapper();
                String json;
                try {
                    json = objectMapper.writeValueAsString(optionalBook.get());
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
                redisUtil.set("book" + bid, json);
            }

            return optionalBook;
        } else {
            LOGGER.info(bid + " is in cache");

            book = JSONArray.parseObject(b.toString(), Book.class);

            return Optional.of(book);
        }

    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);

        LOGGER.info(book.getBid() + " is saved");

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            redisUtil.set("book" + book.getBid(), objectMapper.writeValueAsString(book));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}