package com.example.demo.controller;

import com.example.demo.controller.dto.board.AddArticleRequest;
import com.example.demo.controller.dto.board.ArticlesResponse;
import com.example.demo.domain.BoardEntity;
import com.example.demo.domain.FileEntity;
import com.example.demo.service.BoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
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

        // 줄바꿈
        String changeString = article.getContent().replace("\n", "<br>");

        article.setContent(changeString);
        model.addAttribute("article", article);
        model.addAttribute("id", id);

        // 뷰 목록 리스트에 추가
        String loginUser = (String) session.getAttribute("userId");

        if (loginUser != null) {
            List<ArticlesResponse> list = (List<ArticlesResponse>) session.getAttribute("recentlyView");
            if (list == null) {
                list = new ArrayList<>();
            }

            list.add(article);
            session.setAttribute("recentlyView", list);
        }

        FileEntity attach = boardService.findFileById(id);

        if (!(attach == null)) {
            model.addAttribute("attach", attach);
        }

        return "board/view";
    }

    @GetMapping("/recent-view")
    public String recentlyView(Model model) {
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
    public String insertArticle(@ModelAttribute AddArticleRequest request, Long parentId, @RequestParam("file") MultipartFile file) {

        boardService.insetArticle(request, parentId);

        if (!file.isEmpty()) {
            try {
                String uploadDir = "C:/Temp";
                File uploadPath = new File(uploadDir);
                String path = uploadPath.toString();

                if (!uploadPath.exists()) {
                    uploadPath.mkdirs();
                }

                String originFileName = file.getOriginalFilename();
                String downFileName = UUID.randomUUID() + "_" + originFileName;
                String filePath = uploadPath + "/" + downFileName;

                File dest = new File(filePath);
                file.transferTo(dest);
                BoardEntity lastEntity = boardService.getArticle(parentId);
                boardService.uploadFile(path, originFileName, downFileName, lastEntity.getId());
            } catch (IOException e) {
                throw new RuntimeException("파일 첨부 실패함요");
            }
        }
        return "redirect:/board";
    }

    @GetMapping("/download/{downName}")
    public void downloadFile(@PathVariable String downName, HttpServletResponse response) {

        try {
            String filePath = "C:/Temp/" + downName;
            FileInputStream inputStream = new FileInputStream(filePath);

            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition", "attachment; filename=" + downName);
            response.setHeader("Content-Length", String.valueOf(new File(filePath).length()));

            OutputStream outputStream = response.getOutputStream();

            byte[] data = new byte[1024];

            while (true) {
                int num = inputStream.read(data);
                outputStream.write(data, 0, num);
                if (num == -1) break;
            }

            outputStream.flush();
            outputStream.close();
            inputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
