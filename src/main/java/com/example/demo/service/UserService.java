package com.example.demo.service;

import com.example.demo.controller.dto.user.AddUserRequest;
import com.example.demo.controller.dto.user.UpdateUserRequest;
import com.example.demo.domain.user.UserEntity;
import com.example.demo.domain.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public UserEntity save(AddUserRequest request){
        return userRepository.save(request.toEntity());
    }

    public List<UserEntity> findAll(){
        return userRepository.findAll();
    }

    public UserEntity findById(Long id){
        return userRepository.findById(id);
    }

    public List<UserEntity> findByName(String name){
        return userRepository.findByUser(name);
    }

    public void deleteById(Long id){
        userRepository.deleteById(id);
    }

    public void updateById(Long id, UpdateUserRequest request){
        userRepository.updateById(id, request);
    }
}
