package com.example.demo.controller.board;


import com.example.demo.domain.BoardEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddBoardRequest {
    private String name;
    private String title;
    private String content;

    public BoardEntity toEntity(){
        return new BoardEntity(null, name, title, content, null, 0, 0, null, null);
    }
}
