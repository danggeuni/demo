package com.example.demo.controller.dto;


import com.example.demo.domain.BoardEntity;
import lombok.*;

@Data
public class AddBoardRequest {
    private String name;
    private String title;
    private String content;
    private Long group;

    public BoardEntity toEntity(){
        return new BoardEntity(null, name, title, content, group, 0, 0, null, null);
    }
}
