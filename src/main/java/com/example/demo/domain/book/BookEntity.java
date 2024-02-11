package com.example.demo.domain.book;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BookEntity {
    private final Long id;
    private final String name;
    private final String author;
    private final int code;
    private final Boolean isRental;
}
