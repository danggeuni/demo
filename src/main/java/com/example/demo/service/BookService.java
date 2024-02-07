package com.example.demo.service;


import com.example.demo.controller.dto.AddBookRequest;
import com.example.demo.controller.dto.PagingResponse;
import com.example.demo.domain.BookEntity;
import com.example.demo.domain.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public BookEntity save(AddBookRequest request){
        return bookRepository.save(request.toEntity());
    }

    public List<BookEntity> findAll(){
        return bookRepository.findAll();
    }

    public BookEntity findById(Long id){
        return bookRepository.findById(id);
    }

    public List<BookEntity> findByName(String name){
        return bookRepository.findByName(name);
    }

    public void deleteById(Long id){
        bookRepository.deleteById(id);
    }

    public void rentalBook(Long bookId, Long userId){
        bookRepository.rentalBook(bookId, userId);
    }

    public void returnBook(Long bookId){
        bookRepository.returnBook(bookId);
    }

    public PagingResponse<BookEntity> paging(int pageSize, int pageNumber, String searchQuery){
        List<BookEntity> data = bookRepository.paging(pageSize, pageNumber);
        List<BookEntity> searching = bookRepository.searchByName(searchQuery);
        int totalPage = bookRepository.getTotalPage(pageSize);

        return new PagingResponse<>(data,searching ,totalPage);
    }

    public List<BookEntity> searchByName(String searchQuery){
        return bookRepository.searchByName(searchQuery);
    }
}
