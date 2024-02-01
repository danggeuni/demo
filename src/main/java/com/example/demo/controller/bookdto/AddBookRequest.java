package com.example.demo.controller.bookdto;


import com.example.demo.domain.book.BookEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddBookRequest {

    private String name;
    private String author;
    private int categoryCode;
    private Boolean isRental;

    public BookEntity toEntity(){
        return new BookEntity(null, name, author, categoryCode, false);
    }
}
