package com.example.demo.controller;

import com.example.demo.controller.dto.user.LoginUserRequest;
import com.example.demo.controller.dto.user.RegisterUserRequest;
import com.example.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class UserController {

    private final UserService userService;
    private final HttpSession session;

    public UserController(UserService userService, HttpSession session){
        this.userService = userService;
        this.session = session;
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

    @GetMapping("/login")
    public String loginUser(Model model){
        model.addAttribute("data", new LoginUserRequest());
        return "user/login";
    }

    @PostMapping("/login")
    public String joinUser(@ModelAttribute LoginUserRequest request){
        userService.loginUser(request);
        session.setAttribute("userId", request.getId());

        return "redirect:/board";
    }

    @GetMapping("/logout")
    public String logoutUser(Model model){
        session.invalidate();
        return "redirect:/board";
    }
}
