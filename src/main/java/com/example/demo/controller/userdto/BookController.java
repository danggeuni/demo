package com.example.demo.controller.userdto;

import com.example.demo.controller.bookdto.PageResultResponse;
import com.example.demo.domain.book.BookEntity;
import com.example.demo.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping("/books")
    public String showBookPage(@RequestParam(defaultValue = "20") int pageSize,
                                                       @RequestParam(defaultValue = "1") int pageNumber, Model model){

        PageResultResponse<BookEntity> data = bookService.pagingResult(pageSize, pageNumber);

        model.addAttribute("pageSize", pageSize);
        model.addAttribute("pageNumber", pageNumber);
        model.addAttribute("totalSize", data.getTotalSize());
        model.addAttribute("data", data.getData());

        return "bookPage";
    }
}
