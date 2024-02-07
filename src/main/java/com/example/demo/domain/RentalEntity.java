package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class RentalEntity {
    private final Long id;
    private final Long userId;
    private final Long bookId;
    private final LocalDateTime rentalDate;
    private final LocalDateTime returnDate;
}
