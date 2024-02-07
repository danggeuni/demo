package com.example.demo.controller.dto;

import com.example.demo.domain.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddBookRequest {

    private String name;
    private String author;
    private Long category;

    public BookEntity toEntity(){
        return new BookEntity(null, name, author, category, false);
    }
}
