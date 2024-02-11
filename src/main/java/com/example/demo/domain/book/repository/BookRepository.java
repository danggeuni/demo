package com.example.demo.domain.book.repository;


import com.example.demo.domain.book.BookEntity;
import com.example.demo.domain.book.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class BookRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public CategoryEntity save(CategoryEntity entity){
        jdbcTemplate.update("INSERT INTO BOOK_CATEGORY (CODE, NAME) VALUES (?, ?)", entity.getCode(), entity.getName());

        return entity;
    }

    public List<CategoryEntity> findALl(){
        return jdbcTemplate.query("SELECT * FROM BOOK_CATEGORY", categoryEntityRowMapper());
    }

    public CategoryEntity findByCode(Long id){
        return jdbcTemplate.queryForObject("SELECT * FROM BOOK_CATEGORY WHERE CODE = ?",
                new Object[]{id}, categoryEntityRowMapper());
    }

    public void deleteByCode(Long code){
        jdbcTemplate.update("DELETE FROM BOOK_CATEGORY WHERE CODE = ?", code);
    }

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public BookEntity save(BookEntity entity) {
        jdbcTemplate.update("INSERT INTO BOOK (NAME, AUTHOR, CATEGORY_CODE, IS_RENTAL) VALUES (?, ?, ?, ?)",
                entity.getName(), entity.getAuthor(), entity.getCode(), entity.getIsRental());

        return entity;
    }

    public List<BookEntity> findAll() {
        return jdbcTemplate.query("SELECT * FROM BOOK", bookEntityRowMapper());
    }

    public BookEntity findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM BOOK WHERE ID = ?", new Object[]{id}, bookEntityRowMapper());
    }

    public List<BookEntity> findByName(String name) {
        return jdbcTemplate.query("SELECT * FROM BOOK WHERE NAME = ?", new Object[]{name}, bookEntityRowMapper());
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM BOOK WHERE ID = ?", id);
    }

//    책 대여
    @Transactional
    public void rentalBook(Long userId, Long bookId){
        int update = jdbcTemplate.update("UPDATE BOOK SET IS_RENTAL = TRUE WHERE ID = ? AND IS_RENTAL = FALSE", bookId);

        if(update == 0){
            throw new RuntimeException("책 대여에 실패했슴다 --;");
        }

        jdbcTemplate.update("INSERT INTO RENTAL (USER_ID, BOOK_ID, RENTAL_DATE) VALUES (?, ?, ?)", userId, bookId, LocalDateTime.now());
    }

//    책 반납
    @Transactional
    public void returnBook(Long bookId){
        int update = jdbcTemplate.update("UPDATE BOOK SET IS_RENTAL = FALSE WHERE ID = ? AND IS_RENTAL = TRUE", bookId);

        if(update == 0){
         throw new RuntimeException("책 반납에 실패했슴다 --;");
        }

        jdbcTemplate.update("UPDATE RENTAL SET RETURN_DATE = ? WHERE BOOK_ID = ? AND RETURN_DATE IS NULL ", LocalDateTime.now(), bookId);
    }

//    책 페이징
public List<BookEntity> paging(int pageSize, int pageNumber, String searchQuery) {
    int offset = (pageNumber - 1) * pageSize;

    if (searchQuery == null) {
        return jdbcTemplate.query("SELECT * FROM BOOK LIMIT ? OFFSET ?",
                new Object[]{pageSize, offset}, bookEntityRowMapper());
    } else {
        return jdbcTemplate.query("SELECT * FROM BOOK WHERE NAME LIKE ? LIMIT ? OFFSET ? ",
                new Object[]{"%" + searchQuery + "%", pageSize, offset}, bookEntityRowMapper());
    }

}
    public int getTotalPage(String searchQuery, int pageSize) {
        int totalPage;

        if (searchQuery == null) {
            totalPage = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM BOOK", Integer.class);
        } else {
            totalPage = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM BOOK WHERE NAME LIKE ?",
                    new Object[]{"%" + searchQuery + "%"}, Integer.class);
        }

        return (int) Math.ceil((double) totalPage / pageSize);
    }

    public RowMapper<CategoryEntity> categoryEntityRowMapper(){
        return (rs, rowNum) -> new CategoryEntity(rs.getInt("CODE"), rs.getString("NAME"));
    }

    RowMapper<BookEntity> bookEntityRowMapper() {
        return (rs, rowNum) -> new BookEntity(rs.getLong("ID"), rs.getString("NAME"),
                rs.getString("AUTHOR"), rs.getInt("CATEGORY_CODE"), rs.getBoolean("IS_RENTAL"));
    }
}
