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

    //    페이징 처리 (pageSize : 개수, pageNumber : 페이지 번호)
    public List<BookEntity> paging(int pageSize, int pageNumber){
        int offset = (pageNumber - 1) * pageSize;
        return jdbcTemplate.query("SELECT * FROM BOOK LIMIT ? OFFSET ?", new Object[]{pageSize, offset}, bookEntityRowMapper());
    }

    public int totalPage(int pageSize){
        int count = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM BOOK",Integer.class);

        return (int) Math.ceil((double) count / pageSize);
    }

    RowMapper<BookEntity> bookEntityRowMapper() {
        return (rs, rowNum) -> {
            return new BookEntity(rs.getLong("ID"), rs.getString("NAME"), rs.getString("AUTHOR"),
                    rs.getInt("CATEGORY_CODE"), rs.getBoolean("IS_RENTAL"));
        };
    }
}
