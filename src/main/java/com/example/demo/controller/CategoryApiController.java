package com.example.demo.controller;


import com.example.demo.controller.categorydto.AddCategoryRequest;
import com.example.demo.controller.categorydto.CategoryResponse;
import com.example.demo.controller.categorydto.UpdateCategoryRequest;
import com.example.demo.domain.category.CategoryEntity;
import com.example.demo.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class CategoryApiController {

    private final CategoryService categoryService;

    @PostMapping("/api/category")
    public ResponseEntity<CategoryEntity> addCategory(@RequestBody AddCategoryRequest request){
        CategoryEntity savedEntity = categoryService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedEntity);
    }

    @GetMapping("/api/category")
    public ResponseEntity<List<CategoryResponse>> allCategory(){
        List<CategoryResponse> category = categoryService.findAll().stream()
                .map(CategoryResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .body(category);
    }

    @GetMapping("/api/category/{code}")
    public ResponseEntity<CategoryEntity> category(@PathVariable long code){
        CategoryEntity entity = categoryService.findById(code);

        return ResponseEntity.ok()
                .body(entity);
    }

    @DeleteMapping("/api/category/{code}")
    public ResponseEntity<Void> deleteCategory(@PathVariable long code){
        categoryService.deleteById(code);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/category/{code}")
    public ResponseEntity<CategoryEntity> updateCategory(@PathVariable long code, @RequestBody UpdateCategoryRequest request){
        CategoryEntity entity = categoryService.updateById(code, request);

        return ResponseEntity.ok()
                .body(entity);
    }
}
