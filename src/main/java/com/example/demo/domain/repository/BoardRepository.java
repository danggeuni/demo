package com.example.demo.domain.repository;

import com.example.demo.domain.BoardEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class BoardRepository {

    private final JdbcTemplate jdbcTemplate;

    public BoardRepository(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public BoardEntity insertBoard(BoardEntity entity){
        
        return entity;
    }
}
