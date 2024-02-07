package com.example.demo.controller;


import com.example.demo.controller.dto.PagingResponse;
import com.example.demo.domain.BookEntity;
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

    @GetMapping("/books")
    public String showBooks(@RequestParam(required = false) String searchQuery, @RequestParam(defaultValue = "20") int pageSize, @RequestParam(defaultValue = "1") int pageNumber, Model model){
        PagingResponse<BookEntity> data = bookService.paging(pageSize, pageNumber,searchQuery);

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("data",data.getData());
        model.addAttribute("totalPages", data.getTotalPage());
        model.addAttribute("searchQuery", data.getSearching());

        return "bookPage";
    }
}
