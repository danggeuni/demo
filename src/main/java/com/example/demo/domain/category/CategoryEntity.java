package com.example.demo.domain.category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
public class CategoryEntity {
    private final Long code;
    private final String name;
}
