package com.example.demo.domain.book;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CategoryEntity {
    private final int code;
    private final String name;
}
