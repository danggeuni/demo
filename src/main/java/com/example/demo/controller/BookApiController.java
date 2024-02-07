package com.example.demo.controller;

import com.example.demo.controller.dto.AddBookRequest;
import com.example.demo.domain.BookEntity;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BookApiController {

    private final BookService bookService;

    @PostMapping("/api/books")
    public ResponseEntity<BookEntity> addBook(@RequestBody AddBookRequest request){
        BookEntity entity = bookService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @GetMapping("/api/books")
    public ResponseEntity<List<BookEntity>> findBooks(){
        List<BookEntity> entities = bookService.findAll();

        return ResponseEntity.ok().body(entities);
    }

    @GetMapping("/api/books/{id}")
    public ResponseEntity<BookEntity> findBookById(@PathVariable Long id){
        BookEntity entity = bookService.findById(id);

        return ResponseEntity.ok().body(entity);
    }

    @GetMapping("/api/books/")
    public ResponseEntity<List<BookEntity>> findBookByName(@RequestParam String name){
        List<BookEntity> entity = bookService.findByName(name);

        return ResponseEntity.ok().body(entity);
    }

    @DeleteMapping("/api/books/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id){
        bookService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/api/books/{bookId}/rental")
    public ResponseEntity<Void> rentalBook(@PathVariable Long bookId, @RequestParam Long userId){
        bookService.rentalBook(bookId, userId);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/books/{bookId}/rental")
    public ResponseEntity<Void> returnBook(@PathVariable Long bookId){
        bookService.returnBook(bookId);

        return ResponseEntity.ok().build();
    }
}
