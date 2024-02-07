package com.example.demo.domain.repository;

import com.example.demo.domain.BookEntity;
import com.example.demo.domain.RentalEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class BookRepository {

    private final JdbcTemplate jdbcTemplate;

    public BookRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }


//    책을 추가한다.
    public BookEntity save(BookEntity entity){
        jdbcTemplate.update("INSERT INTO BOOK (NAME, AUTHOR, CATEGORY_CODE) VALUES (?, ?, ?)",
                entity.getName(), entity.getAuthor(), entity.getCategory());

        return entity;
    }

//    모든 책을 불러온다.
    public List<BookEntity> findAll(){
        return jdbcTemplate.query("SELECT * FROM BOOK", bookEntityRowMapper());
    }

//    하나의 책을 불러온다. (ID, NAME)
    public BookEntity findById(Long id){
        return jdbcTemplate.queryForObject("SELECT * FROM BOOK WHERE ID = ?", new Object[]{id}, bookEntityRowMapper());
    }

    public List<BookEntity> findByName(String name){
        return jdbcTemplate.query("SELECT * FROM BOOK WHERE NAME = ?", new Object[]{name}, bookEntityRowMapper());
    }

//    책을 삭제한다.
    public void deleteById(Long id){
        jdbcTemplate.update("DELETE FROM BOOK WHERE ID = ?", id);
    }

//    책을 대여한다.
    @Transactional
    public void rentalBook(Long bookId, Long userId){
        int update = jdbcTemplate.update("UPDATE BOOK SET IS_RENTAL = TRUE WHERE ID = ? AND IS_RENTAL = FALSE", bookId);

        if(update == 0){
            throw new RuntimeException("책 대여에 실패하였습니다.");
        }

        jdbcTemplate.update("INSERT INTO BOOK_RENTAL (USER_ID, BOOK_ID, RENTAL_DATE) VALUES (?, ?, ?)", userId, bookId, LocalDateTime.now());
    }

//    책을 반납한다.
    @Transactional
    public void returnBook(Long bookId){
        int update = jdbcTemplate.update("UPDATE BOOK SET IS_RENTAL = FALSE WHERE ID = ? AND IS_RENTAL = TRUE", bookId);

        if (update == 0){
            throw new RuntimeException("책 반납에 실패하였습니다.");
        }

        jdbcTemplate.update("UPDATE BOOK_RENTAL SET RETURN_DATE = ? WHERE BOOK_ID = ? AND RETURN_DATE IS NULL", LocalDateTime.now(), bookId);
    }

//    페이징 처리를 한다.
    public List<BookEntity> paging(int pageSize, int pageNumber){
        int offset = (pageNumber - 1) * pageSize;

        return jdbcTemplate.query("SELECT * FROM BOOK LIMIT ? OFFSET ?", new Object[]{pageSize, offset}, bookEntityRowMapper());
    }

    public int getTotalPage(int pageSize){
        int totalPage = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM BOOK", Integer.class);

        return (int) Math.ceil((double) totalPage / pageSize);
    }

//    검색을 구현한다.
    public List<BookEntity> searchByName(String searchQuery){
        return jdbcTemplate.query("SELECT * FROM BOOK WHERE NAME LIKE ?", new Object[]{"%" + searchQuery + "%"}, bookEntityRowMapper());
    }


    RowMapper<BookEntity> bookEntityRowMapper(){
        return (rs, rowNum) -> new BookEntity(rs.getLong("ID"), rs.getString("NAME"),
                rs.getString("AUTHOR"), rs.getLong("CATEGORY_CODE"), rs.getBoolean("IS_RENTAL"));
    }

}
