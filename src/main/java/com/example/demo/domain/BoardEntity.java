package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class BoardEntity {
    private final Long id;
    private final String name;
    private final String title;
    private final String content;
    private final Long group;
    private final int sortKey;
    private final int depth;
    private final LocalDateTime created_at;
    private final LocalDateTime modified_at;
}
