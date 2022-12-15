package com.example.backend.dto.solr;

import org.apache.solr.client.solrj.beans.Field;

public class Book {
    @Field
    private Integer id;
    @Field
    private String name;
    @Field
    private String type;
    @Field
    private String writers;
    @Field
    private String description;

    Book() {

    }

    public Book(com.example.backend.entity.Book book) {
        id = book.getBid();
        name = book.getName();
        type = book.getType();
        writers = book.getWriters();
        description = book.getDescription();
    }
}
