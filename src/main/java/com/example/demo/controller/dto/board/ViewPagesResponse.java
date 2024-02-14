package com.example.demo.controller.dto.board;

import lombok.Data;

import java.util.List;

@Data
public class ViewPagesResponse<BoardEntity> {
    private final List<BoardEntity> data;
    private final int pages;

    public ViewPagesResponse(List<BoardEntity> data, int pages){
        this.data = data;
        this.pages = pages;
    }
}
