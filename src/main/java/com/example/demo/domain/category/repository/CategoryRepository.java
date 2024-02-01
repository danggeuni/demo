package com.example.demo.domain.category.repository;

import com.example.demo.controller.categorydto.UpdateCategoryRequest;
import com.example.demo.domain.category.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public CategoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public CategoryEntity save(CategoryEntity request) {
        jdbcTemplate.update("INSERT INTO BOOK_CATEGORY (CODE, NAME) VALUES (?, ?)", request.getCode(), request.getName());
        return request;
    }

    public List<CategoryEntity> findAll() {
        return jdbcTemplate.query("SELECT * FROM BOOK_CATEGORY", categoryEntityRowMapper());
    }

    public CategoryEntity findById(Long code){
        return jdbcTemplate.queryForObject("SELECT * FROM BOOK_CATEGORY WHERE CODE = ?", new Object[]{code}, categoryEntityRowMapper());
    }

    public void deleteById(Long code){
        jdbcTemplate.update("DELETE FROM BOOK_CATEGORY WHERE CODE = ?", code);
    }

    public CategoryEntity updateById(Long code, UpdateCategoryRequest request){
        jdbcTemplate.update("UPDATE BOOK_CATEGORY SET NAME = ? WHERE CODE = ?", request.getName(), code);
        return findById(code);
    }

    RowMapper<CategoryEntity> categoryEntityRowMapper() {
        return (rs, rowNum) -> {
            return new CategoryEntity(rs.getLong("CODE"), rs.getString("NAME"));
        };
    }
}
