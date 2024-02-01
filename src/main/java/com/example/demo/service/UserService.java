package com.example.demo.service;

import com.example.demo.controller.userdto.AddUserRequest;
import com.example.demo.controller.userdto.UpdateUserRequest;
import com.example.demo.domain.user.UserEntity;
import com.example.demo.domain.user.repsitory.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity save(AddUserRequest request) {
        return userRepository.addUser(request.toEntity());
    }

    public List<UserEntity> findAll() {
        return userRepository.findByAll();
    }

    public UserEntity findById(long id) {
        return userRepository.findById(id);
    }

    public List<UserEntity> findByName(String name) {
        return userRepository.findByName(name);
    }

    public void deleteById(long id) {
        userRepository.deleteById(id);
    }

    public UserEntity updateById(long id, UpdateUserRequest request) {
        return userRepository.updateById(id, request);
    }
}
