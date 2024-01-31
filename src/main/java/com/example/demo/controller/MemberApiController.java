package com.example.demo.controller;


import com.example.demo.controller.dto.AddUserRequest;
import com.example.demo.domain.user.UserEntity;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class MemberApiController {

    private final UserService userService;

    @PostMapping("/api/users")
    public ResponseEntity<UserEntity> addUser(@RequestBody AddUserRequest request){
        UserEntity savedUser = userService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedUser);
    }
}
