package com.example.demo.controller.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
@AllArgsConstructor
public class PagingResponse<T> {
    private final List<T> data;
    private final List<T> searching;
    private final int totalPage;
}
