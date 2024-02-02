package com.example.demo.domain.book;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class RentalEntity {

    private final Long id;
    private final Long memberId;
    private final Long bookId;
    private final LocalDateTime rentalDate;

    public RentalEntity(Long id, Long memberId, Long bookId, LocalDateTime rentalDate){
        this.id = id;
        this.memberId = memberId;
        this.bookId = bookId;
        this.rentalDate = rentalDate;
    }
}
