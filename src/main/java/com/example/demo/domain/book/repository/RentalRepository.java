package com.example.demo.domain.book.repository;

import com.example.demo.domain.book.BookEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class RentalRepository {

    private final JdbcTemplate jdbcTemplate;

    public RentalRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    @Transactional
    public void rental(Long bookId, Long userId){
       int update = jdbcTemplate.update("UPDATE BOOK SET IS_RENTAL = TRUE WHERE ID = ? AND IS_RENTAL = FALSE", bookId);

       if(update == 0){
           throw new RuntimeException("책 대여에 실패했습니다.");
       }

       jdbcTemplate.update("INSERT INTO BOOK_RENTAL (MEMBER_ID, BOOK_ID, RENTAL_DATE) VALUES (?, ?, ?)", userId, bookId, LocalDateTime.now());
    }

    @Transactional
    public void returnBook(Long bookId){
        int update = jdbcTemplate.update("UPDATE BOOK SET IS_RENTAL = FALSE WHERE ID = ? AND IS_RENTAL = TRUE", bookId);

        if(update == 0){
            throw new RuntimeException("책 반납에 실패했습니다.");
        }

        jdbcTemplate.update("DELETE FROM BOOK_RENTAL WHERE BOOK_ID = ?", bookId);
    }
}
