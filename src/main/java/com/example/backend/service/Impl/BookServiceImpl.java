package com.example.backend.service.Impl;

import com.example.backend.dao.BookDao;
import com.example.backend.dto.BookWithSell;
import com.example.backend.entity.Book;
import com.example.backend.service.BookService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.client.solrj.response.UpdateResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.params.MapSolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

import static com.example.backend.util.SolrUtil.getSolrClient;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;


    public Iterable<Book> getBooks() {
        return bookDao.findAll();
    }


    public Iterable<Book> getBooksByKeyword(String keyword) throws SolrServerException, IOException {
        int isbn;
        try {

            isbn = Integer.parseInt(keyword);
        } catch (Exception e) {
            isbn = 0;
        }
        final SolrClient client = getSolrClient();

        final Map<String, String> queryParamMap = new HashMap<>();
        queryParamMap.put("q", "description:" + keyword + " writers: " + keyword + " name: " + keyword + " || type: " + keyword);
        queryParamMap.put("fl", "id");
        queryParamMap.put("sort", "score desc");
        MapSolrParams queryParams = new MapSolrParams(queryParamMap);

        final QueryResponse response = client.query("gettingstarted", queryParams);
        final SolrDocumentList documents = response.getResults();

        LinkedList<Book> books = new LinkedList<>();
        for (SolrDocument document : documents) {
            final String id = (String) document.getFirstValue("id");
            bookDao.findById(Integer.valueOf(id)).map(books::add);
        }
        return books;
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
    public String addIndex(Book book) {
        final SolrClient client = getSolrClient();
        com.example.backend.dto.solr.Book b = new com.example.backend.dto.solr.Book(book);
        try {
            final UpdateResponse response = client.addBean("gettingstarted", b);
        } catch (IOException | SolrServerException e) {
            throw new RuntimeException(e);
        }
        return "Ok";
    }

    @Override
    public String reloadIndex() {
        Iterable<Book> books = bookDao.findAll();
        final SolrClient client = getSolrClient();

        books.forEach(book -> {
            final com.example.backend.dto.solr.Book b = new com.example.backend.dto.solr.Book(book);
            try {
                final UpdateResponse response = client.addBean("gettingstarted", b);
            } catch (IOException | SolrServerException e) {
                throw new RuntimeException(e);
            }
        });

        return "Ok";
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
        addIndex(book);
        return "Ok";
    }
}
