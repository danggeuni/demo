package com.example.demo.service;

import com.example.demo.controller.dto.AddUserRequest;
import com.example.demo.domain.user.UserEntity;
import com.example.demo.domain.user.repsitory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity save(AddUserRequest request){
        return userRepository.addUser(request.toEntity());
    }
}
