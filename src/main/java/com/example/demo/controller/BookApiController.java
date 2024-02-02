package com.example.demo.controller;

import com.example.demo.controller.bookdto.AddBookRequest;
import com.example.demo.controller.bookdto.BookResponse;
import com.example.demo.domain.book.BookEntity;
import com.example.demo.service.BookService;
import com.example.demo.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class BookApiController {

    private final BookService bookService;
    private final RentalService rentalService;

    @PostMapping("/api/books")
    public ResponseEntity<BookEntity> addBook(@RequestBody AddBookRequest request){
        BookEntity entity = bookService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(entity);
    }

    @GetMapping("/api/books")
    public ResponseEntity<List<BookResponse>> allBooks(){
        List<BookResponse> bookResponses = bookService.findAll().stream()
                .map(BookResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .body(bookResponses);
    }

    @GetMapping("/api/books/{id}")
    public ResponseEntity<BookEntity> findBook(@PathVariable Long id){
        BookEntity entity = bookService.findById(id);

        return ResponseEntity.ok()
                .body(entity);
    }

    @GetMapping("/api/books/")
    public ResponseEntity<List<BookEntity>> findBookByName(@RequestParam String name){
        List<BookEntity> entity = bookService.findByName(name);

        return ResponseEntity.ok()
                .body(entity);
    }

    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookService.deleteById(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/books/{id}/rental")
    public ResponseEntity<Void> rentBook(@PathVariable Long id, @RequestParam Long userId){
        rentalService.rental(id, userId);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/api/books/{id}/rental")
    public ResponseEntity<Void> returnBook(@PathVariable Long id){
        rentalService.returnBook(id);

        return ResponseEntity.ok().build();
    }
}
