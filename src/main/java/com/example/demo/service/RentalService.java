package com.example.demo.service;

import com.example.demo.domain.book.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final RentalRepository rentalRepository;

    public void rental(Long bookId, Long userId){
        rentalRepository.rental(bookId, userId);
    }

    public void returnBook(Long bookId){
        rentalRepository.returnBook(bookId);
    }
}
