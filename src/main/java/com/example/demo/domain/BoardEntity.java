package com.example.demo.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@Getter
@RequiredArgsConstructor
public class BoardEntity {
    private final Long id;
    private final String name;
    private final String title;
    private final String content;
    private final Long groupId;
    private final int sortKey;
    private final int depth;
    private final LocalDateTime createDate;
    private final LocalDateTime updateDate;
}
