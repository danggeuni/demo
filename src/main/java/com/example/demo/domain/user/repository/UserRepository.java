package com.example.demo.domain.user.repository;

import com.example.demo.controller.userdto.UpdateUserRequest;
import com.example.demo.domain.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserEntity addUser(UserEntity entity) {
        jdbcTemplate.update("INSERT INTO USER (NAME, ADDRESS, PHONE) VALUES(?, ?, ?)", entity.getName(), entity.getAddress(), entity.getPhone());
        return entity;
    }

    public List<UserEntity> findByAll() {
        return jdbcTemplate.query("SELECT * FROM USER", userEntityRowMapper());
    }

    public UserEntity findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM USER WHERE ID = ?", new Object[]{id}, userEntityRowMapper());
    }

    public List<UserEntity> findByName(String name) {
        return jdbcTemplate.query("SELECT * FROM USER WHERE NAME = ?", new Object[]{name}, userEntityRowMapper());
    }

    public void deleteById(Long id) {
        jdbcTemplate.update("DELETE FROM USER WHERE ID = ?", id);
    }

    @Transactional
    public UserEntity updateById(Long id, UpdateUserRequest request) {
        jdbcTemplate.update("UPDATE USER SET NAME = ?, ADDRESS = ?, PHONE = ? WHERE ID = ?", request.getName(), request.getAddress(), request.getPhone(), id);
        return jdbcTemplate.queryForObject("SELECT * FROM USER WHERE ID = ?", new Object[]{id}, userEntityRowMapper());
    }

    RowMapper<UserEntity> userEntityRowMapper() {
        return (rs, rowNum) -> new UserEntity(rs.getLong("ID"), rs.getString("Name"),
                rs.getString("ADDRESS"), rs.getString("PHONE"));
    }
}
