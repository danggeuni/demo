package com.example.demo.controller.categorydto;


import com.example.demo.domain.category.CategoryEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddCategoryRequest {
    Long code;
    String name;

    public CategoryEntity toEntity(){
        return new CategoryEntity(code, name);
    }
}
