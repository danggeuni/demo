package com.example.demo.domain.book.repository;

import com.example.demo.domain.book.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public BookEntity addBook(BookEntity entity){
        jdbcTemplate.update("INSERT INTO BOOK (NAME, AUTHOR, CATEGORY_CODE, IS_RENTAL) VALUES (?, ?, ?, ?)", entity.getName(), entity.getAuthor(), entity.getCategoryCode(), entity.getIsRental());
        return entity;
    }
}
