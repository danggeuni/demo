package com.example.demo.service;


import com.example.demo.controller.categorydto.AddCategoryRequest;
import com.example.demo.controller.categorydto.UpdateCategoryRequest;
import com.example.demo.domain.category.CategoryEntity;
import com.example.demo.domain.category.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryEntity save(AddCategoryRequest request) {
        return categoryRepository.save(request.toEntity());
    }

    public List<CategoryEntity> findAll(){
        return categoryRepository.findAll();
    }

    public CategoryEntity findById(long code){
        return categoryRepository.findById(code);
    }

    public void deleteById(long code){
        categoryRepository.deleteById(code);
    }

    public CategoryEntity updateById(long id, UpdateCategoryRequest request){
        return categoryRepository.updateById(id, request);
    }
}
