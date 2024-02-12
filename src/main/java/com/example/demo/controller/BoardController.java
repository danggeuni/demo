package com.example.demo.controller;


import com.example.demo.controller.dto.AddBoardRequest;
import com.example.demo.controller.dto.BoardListResponse;
import com.example.demo.domain.BoardEntity;
import com.example.demo.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    @GetMapping
    public String getBoardList(Model model){
        List<BoardListResponse> list = boardService.getBoardList().stream().map(BoardListResponse::new).collect(Collectors.toList());
        model.addAttribute("list", list);

        return "board/list";
    }

    @GetMapping("/{id}")
    public String getBoard(Model model, @PathVariable Long id){
        BoardEntity entity = boardService.getBoard(id);
        BoardListResponse board = new BoardListResponse(entity);
        model.addAttribute("board", board);
        return "board/view";
    }

    @GetMapping(path = {"/new", "/{parentId}/reply"})
    public String newArticle(Model model, @PathVariable(required = false) Long parentId) {
        model.addAttribute("item", new AddBoardRequest());
        model.addAttribute("parentId", parentId);
        return "board/new";
    }

    @PostMapping("/new")
    public String newArticle(Model model, @ModelAttribute AddBoardRequest request, Long parentId) {
        boardService.insertBoard(request, parentId);
        return "redirect:/board";
    }
}
