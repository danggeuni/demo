package com.example.demo.controller;

import com.example.demo.controller.dto.board.AddArticleRequest;
import com.example.demo.controller.dto.board.ArticlesResponse;
import com.example.demo.domain.BoardEntity;
import com.example.demo.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/board")
public class BoardController {

    private final BoardService boardService;
    private final HttpSession session;

    public BoardController(BoardService boardService, HttpSession session) {
        this.boardService = boardService;
        this.session = session;
    }

    @GetMapping()
    public String boardList(Model model, @RequestParam(defaultValue = "10") int pageSize,
                            @RequestParam(defaultValue = "1") int pageNumber) {
        List<ArticlesResponse> list = boardService.getArticles(pageSize, pageNumber).getData()
                .stream().map(ArticlesResponse::new).collect(Collectors.toList());

        int totalPages = boardService.getArticles(pageSize, pageNumber).getPages();
        String userName = (String) session.getAttribute("userId");

        model.addAttribute("list", list);
        model.addAttribute("totalPages", totalPages);
        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("userName", userName);

        return "board/list";
    }

    @GetMapping("/{id}")
    public String view(Model model, @PathVariable Long id) {
        ArticlesResponse article = new ArticlesResponse(boardService.getArticle(id));

        String changeString = article.getContent().replace("\n", "<br>");

        article.setContent(changeString);
        model.addAttribute("article", article);
        model.addAttribute("id", id);

        String loginUser = (String) session.getAttribute("userId");
        System.out.println(loginUser);
        if (loginUser != null) {
            List<ArticlesResponse> list = (List<ArticlesResponse>) session.getAttribute("recentlyView");
            if (list == null) {
                list = new ArrayList<>();
            }
//        본 목록 추가
            list.add(article);

//        세션에 저장
            session.setAttribute("recentlyView", list);
            System.out.println(session.getAttribute("recentlyView"));
        }

        return "board/view";
    }

    @GetMapping("/recent-view")
    public String recentlyView(Model model){
        List<ArticlesResponse> list = (List<ArticlesResponse>) session.getAttribute("recentlyView");
        String loginUser = (String) session.getAttribute("userId");

        model.addAttribute("list", list);
        model.addAttribute("loginUser", loginUser);

        return "board/recentlyView";
    }

    @GetMapping(path = {"/new", "/{parentId}/reply"})
    public String newArticle(Model model, @PathVariable(required = false) Long parentId) {
        String userName = (String) session.getAttribute("userId");

        model.addAttribute("item", new AddArticleRequest());
        model.addAttribute("parentId", parentId);
        model.addAttribute("userName", userName);

        return "board/new";
    }

    @PostMapping("/new")
    public String insertArticle(@ModelAttribute AddArticleRequest request, Long parentId) {
        boardService.insetArticle(request, parentId);
        return "redirect:/board";
    }
}
