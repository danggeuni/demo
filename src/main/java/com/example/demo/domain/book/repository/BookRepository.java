package com.example.demo.domain.book.repository;

import com.example.demo.domain.book.BookEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public BookEntity addBook(BookEntity entity) {
        jdbcTemplate.update("INSERT INTO BOOK (NAME, AUTHOR, CATEGORY_CODE, IS_RENTAL) VALUES (?, ?, ?, ?)", entity.getName(), entity.getAuthor(), entity.getCategoryCode(), entity.getIsRental());
        return entity;
    }

    public List<BookEntity> findAll(){
        return jdbcTemplate.query("SELECT * FROM BOOK", bookEntityRowMapper());
    }

    public BookEntity findById(Long id){
        return jdbcTemplate.queryForObject("SELECT * FROM BOOK WHERE ID = ?", new Object[]{id}, bookEntityRowMapper());
    }

    public List<BookEntity> findByName(String name){
       return jdbcTemplate.query("SELECT * FROM BOOK WHERE NAME = ?", new Object[]{name}, bookEntityRowMapper());
    }

    public void deleteById(Long id){
        jdbcTemplate.update("DELETE FROM BOOK WHERE ID = ?", id);
    }

    RowMapper<BookEntity> bookEntityRowMapper() {
        return (rs, rowNum) -> {
            return new BookEntity(rs.getLong("ID"), rs.getString("NAME"), rs.getString("AUTHOR"),
                    rs.getInt("CATEGORY_CODE"), rs.getBoolean("IS_RENTAL"));
        };
    }
}
