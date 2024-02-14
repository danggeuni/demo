package com.example.demo.controller;

import com.example.demo.controller.dto.board.AddArticleRequest;
import com.example.demo.controller.dto.board.ArticlesResponse;
import com.example.demo.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;

    public BoardController(BoardService boardService) {
        this.boardService = boardService;
    }

    @GetMapping()
    public String boardList(Model model, @RequestParam(defaultValue = "5") int pageSize,
                            @RequestParam(defaultValue = "1") int pageNumber) {
        List<ArticlesResponse> list = boardService.getArticles(pageSize, pageNumber).getData()
                .stream().map(ArticlesResponse::new).collect(Collectors.toList());

        int totalPages = boardService.getArticles(pageSize, pageNumber).getPages();

        model.addAttribute("list", list);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNumber", pageNumber);

        return "board/list";
    }

    @GetMapping("/{id}")
    public String view(Model model, @PathVariable Long id) {
        ArticlesResponse article = new ArticlesResponse(boardService.getArticle(id));

        String changeString = article.getContent().replace("\n", "<br>");

        article.setContent(changeString);
        model.addAttribute("article", article);
        model.addAttribute("id", id);

        return "board/view";
    }

    @GetMapping(path = {"/new", "/{parentId}/reply"})
    public String newArticle(Model model, @PathVariable(required = false) Long parentId) {
        model.addAttribute("item", new AddArticleRequest());
        model.addAttribute("parentId", parentId);

        return "board/new";
    }

    @PostMapping("/new")
    public String insertArticle(@ModelAttribute AddArticleRequest request, Long parentId) {
        boardService.insetArticle(request, parentId);
        return "redirect:/board";
    }
}
