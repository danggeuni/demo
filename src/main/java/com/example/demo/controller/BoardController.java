package com.example.demo.controller;

import com.example.demo.controller.board.AddBoardRequest;
import com.example.demo.domain.BoardEntity;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @PostMapping("/api/board")
    public ResponseEntity<BoardEntity> addArticle(@RequestBody AddBoardRequest request){
        BoardEntity entity = boardService.insertBoard(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }
}
