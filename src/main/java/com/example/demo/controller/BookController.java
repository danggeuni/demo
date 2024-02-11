package com.example.demo.controller;

import com.example.demo.controller.dto.book.PagingResponse;
import com.example.demo.domain.book.BookEntity;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/book")
    public String paging(@RequestParam(defaultValue = "20") int pageSize, @RequestParam(defaultValue = "1") int pageNumber,
                         @RequestParam(required = false) String searchQuery, Model model){

        PagingResponse<BookEntity> responses = bookService.paging(pageSize, pageNumber, searchQuery);

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("searchQuery", searchQuery);
        model.addAttribute("data", responses.getData());
        model.addAttribute("totalPage", responses.getTotalPage());

        return "bookPage";
    }
}
