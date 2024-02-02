package com.example.demo.controller.bookdto;


import com.example.demo.domain.book.BookEntity;
import lombok.Getter;

@Getter
public class BookResponse {

    private final String name;
    private final String author;
    private final int categoryCode;
    private final boolean isRental;

    public BookResponse(BookEntity bookEntity){
        this.name = bookEntity.getName();
        this.author = bookEntity.getAuthor();
        this.categoryCode = bookEntity.getCategoryCode();
        this.isRental = bookEntity.getIsRental();
    }
}
