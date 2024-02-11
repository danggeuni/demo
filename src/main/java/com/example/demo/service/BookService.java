package com.example.demo.service;


import com.example.demo.controller.dto.book.PagingResponse;
import com.example.demo.domain.book.BookEntity;
import com.example.demo.domain.book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;

    public PagingResponse<BookEntity> paging(int pageSize, int pageNumber, String searchQuery){
        List<BookEntity> data = bookRepository.paging(pageSize, pageNumber, searchQuery);
        int totalPage = bookRepository.getTotalPage(searchQuery, pageSize);

        return new PagingResponse<>(data, totalPage);
    }
}
