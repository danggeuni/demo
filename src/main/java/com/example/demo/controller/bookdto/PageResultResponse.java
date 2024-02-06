package com.example.demo.controller.bookdto;

import lombok.Getter;

import java.util.List;

@Getter
public class PageResultResponse<T> {

    private final List<T> data;
    private final int totalSize;

    public PageResultResponse(List<T> data, int totalSize){
        this.data = data;
        this.totalSize = totalSize;
    }
}
