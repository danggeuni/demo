package com.example.demo.service;


import com.example.demo.controller.bookdto.AddBookRequest;
import com.example.demo.domain.book.BookEntity;
import com.example.demo.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookEntity save(AddBookRequest request){
        return bookRepository.addBook(request.toEntity());
    }
}
