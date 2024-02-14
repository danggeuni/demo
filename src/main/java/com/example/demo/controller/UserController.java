package com.example.demo.controller;

import com.example.demo.controller.dto.user.RegisterUserRequest;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/join")
    public String registerUser(Model model, String confirmPwd){
        model.addAttribute("data", new RegisterUserRequest());
        model.addAttribute("confirmPwd", confirmPwd);
        return "user/join";
    }

    @PostMapping("/join")
    public String createUser(@ModelAttribute RegisterUserRequest request, String confirmPwd){
        userService.insertUser(request, confirmPwd);

        return "redirect:/board";
    }
}
