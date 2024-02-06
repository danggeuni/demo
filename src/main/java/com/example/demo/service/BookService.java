package com.example.demo.service;


import com.example.demo.controller.bookdto.AddBookRequest;
import com.example.demo.controller.bookdto.PageResultResponse;
import com.example.demo.domain.book.BookEntity;
import com.example.demo.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookEntity save(AddBookRequest request) {
        return bookRepository.addBook(request.toEntity());
    }

    public List<BookEntity> findAll() {
        return bookRepository.findAll();
    }

    public BookEntity findById(Long id) {
        return bookRepository.findById(id);
    }

    public List<BookEntity> findByName(String name) {
        return bookRepository.findByName(name);
    }

    public void deleteById(Long id) {
        bookRepository.deleteById(id);
    }

    public PageResultResponse<BookEntity> pagingResult(int pageSize, int pageNumber){
        List<BookEntity> result = bookRepository.paging(pageSize, pageNumber);
        int totalSize = bookRepository.totalPage(pageSize);

        return new PageResultResponse<>(result, totalSize);
    }
}
