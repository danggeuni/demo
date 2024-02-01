package com.example.demo.domain.book;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class BookEntity {
    private final Long id;
    private final String name;
    private final String author;
    private final int categoryCode;
    private final Boolean isRental;

    public BookEntity(Long id, String name, String author, int categoryCode, boolean isRental){
        this.id = id;
        this.name = name;
        this.author = author;
        this.categoryCode = categoryCode;
        this.isRental = isRental;
    }
}
