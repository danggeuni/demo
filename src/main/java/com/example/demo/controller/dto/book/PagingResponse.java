package com.example.demo.controller.dto.book;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;

@Getter
public class PagingResponse<T> {
    private final List<T> data;
    private final int totalPage;

    public PagingResponse(List<T> data, int totalPage){
        this.data = data;
        this.totalPage = totalPage;
    }
}
