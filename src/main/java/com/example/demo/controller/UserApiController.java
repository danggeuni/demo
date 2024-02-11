package com.example.demo.controller;

import com.example.demo.controller.dto.user.AddUserRequest;
import com.example.demo.controller.dto.user.UpdateUserRequest;
import com.example.demo.domain.user.UserEntity;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class UserApiController {

    private final UserService userService;

    @PostMapping("/api/users")
    public ResponseEntity<UserEntity> addUser(@RequestBody AddUserRequest request){
        UserEntity entity = userService.save(request);

        return ResponseEntity.status(HttpStatus.CREATED).body(entity);
    }

    @GetMapping("/api/users")
    public ResponseEntity<List<UserEntity>> findUsers(){
        List<UserEntity> entities = userService.findAll();

        return ResponseEntity.ok().body(entities);
    }

    @GetMapping("/api/users/{id}")
    public ResponseEntity<UserEntity> findUserById(@PathVariable Long id){
        UserEntity entity = userService.findById(id);

        return ResponseEntity.ok().body(entity);
    }

    @GetMapping("/api/users/")
    public ResponseEntity<List<UserEntity>> findUserByName(@RequestParam String name){
        List<UserEntity> entities = userService.findByName(name);

        return ResponseEntity.ok().body(entities);
    }

    @DeleteMapping("/api/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id){
        userService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @PutMapping("/api/users/{id}")
    public ResponseEntity<UserEntity> updateUser(@PathVariable Long id, @RequestBody UpdateUserRequest request){
        userService.updateById(id, request);

        return ResponseEntity.ok().build();
    }
}
