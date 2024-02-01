package com.example.demo.controller;


import com.example.demo.controller.dto.AddUserRequest;
import com.example.demo.controller.dto.UpdateUserRequest;
import com.example.demo.controller.dto.UserResponse;
import com.example.demo.domain.user.UserEntity;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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

    @GetMapping("/api/users")
    public ResponseEntity<List<UserResponse>> users(){
        List<UserResponse> userEntities = userService.findByAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());

        return ResponseEntity.ok()
                .body(userEntities);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserEntity> user(@PathVariable long id){
        UserEntity user = userService.findById(id);

        return ResponseEntity.ok()
                .body(user);
    }

    @GetMapping("/api/users/")
    public ResponseEntity<List<UserEntity>> findByName(@RequestParam String name){
        List<UserEntity> userEntities = userService.findByName(name);

        return ResponseEntity.ok()
                .body(userEntities);
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable long id){
        userService.deleteById(id);

        return ResponseEntity.ok()
                .build();
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable long id, @RequestBody UpdateUserRequest request){
        UserEntity updateUser = userService.updateById(id, request);

        return ResponseEntity.ok()
                .body(updateUser);
    }
}
