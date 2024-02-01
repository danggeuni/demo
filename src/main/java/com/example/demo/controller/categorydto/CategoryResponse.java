package com.example.demo.controller.categorydto;

import com.example.demo.domain.book.CategoryEntity;
import lombok.Getter;

@Getter
public class CategoryResponse {

    private final Long code;
    private final String name;

    public CategoryResponse(CategoryEntity entity){
        this.code = entity.getCode();
        this.name = entity.getName();
    }
}
