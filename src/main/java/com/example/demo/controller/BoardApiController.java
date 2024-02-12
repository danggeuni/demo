package com.example.demo.controller;

import com.example.demo.controller.dto.AddBoardRequest;
import com.example.demo.domain.BoardEntity;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService;

    @PostMapping("/api/board")
    public ResponseEntity<BoardEntity> addArticle(@RequestBody AddBoardRequest request, @RequestParam(required = false) Long parentId){
        BoardEntity entity = boardService.insertBoard(request, parentId);

        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @GetMapping("/api/board/{id}")
    public ResponseEntity<BoardEntity> getBoard(@PathVariable Long id){
        BoardEntity entity = boardService.getBoard(id);

        return ResponseEntity.ok().body(entity);
    }

    @GetMapping("/api/board")
    public ResponseEntity<List<BoardEntity>> gotBoardList(){
        List<BoardEntity> entities = boardService.getBoardList();

        return ResponseEntity.ok().body(entities);
    }
}

