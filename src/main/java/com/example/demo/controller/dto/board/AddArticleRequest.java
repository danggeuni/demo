package com.example.demo.controller.dto.board;

import com.example.demo.domain.BoardEntity;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AddArticleRequest {
    private String name;
    private String title;
    private String content;

    public BoardEntity toEntity(){
        return new BoardEntity(null, name, title, content, null, 0, 0, LocalDateTime.now(),null);
    }
}
