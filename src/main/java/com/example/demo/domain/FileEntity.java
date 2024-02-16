package com.example.demo.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FileEntity {
    private final Long id;
    private final String path;
    private final String originName;
    private final String downName;
    private final Long boardId;
}
