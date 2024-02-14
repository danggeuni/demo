package com.example.demo.domain.repository;

import com.example.demo.domain.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserEntity insertUser(UserEntity entity) {
        jdbcTemplate.update("INSERT INTO USER (USERID, NAME, PASSWORD) VALUES (?, ?, ?)",
                entity.getId(), entity.getName(), entity.getPassword());

        return entity;
    }

    public UserEntity findById(String id) {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM USER WHERE USERID = ?", new Object[]{id}, userEntityRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    RowMapper<UserEntity> userEntityRowMapper() {
        return (rs, rowNum) -> new UserEntity(
                rs.getString("USERID"),
                rs.getString("NAME"),
                rs.getString("PASSWORD")
        );
    }
}
