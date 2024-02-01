package com.example.demo.controller;

import com.example.demo.controller.bookdto.AddBookRequest;
import com.example.demo.domain.book.BookEntity;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class BookApiController {

    private final BookService bookService;

    @PostMapping("/api/books")
    public ResponseEntity<BookEntity> addBook(@RequestBody AddBookRequest request){
        BookEntity entity = bookService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(entity);
    }
}
