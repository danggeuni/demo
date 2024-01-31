package com.example.demo.domain.user.repsitory;


import com.example.demo.controller.dto.AddUserRequest;
import com.example.demo.domain.user.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public UserEntity addUser(UserEntity entity){

        int addUser = jdbcTemplate.update("INSERT INTO USER (NAME, ADDRESS, PHONE) VALUES(?, ?, ?)", entity.getName(), entity.getAddress(), entity.getPhone());

        return entity;
    }

    public UserEntity findById(Long id) {
        return jdbcTemplate.queryForObject("SELECT * FROM USER WHERE ID = ?", new Object[]{id}, (rs, rowNum) -> {
            long pk = rs.getLong("ID");
            String name = rs.getString("NAME");
            String address = rs.getString("ADDRESS");
            String phone = rs.getString("PHONE");
            return new UserEntity(pk, name, address, phone);
        });
    }
}
