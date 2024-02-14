package com.example.demo.service;

import com.example.demo.Encrypt;
import com.example.demo.controller.dto.user.RegisterUserRequest;
import com.example.demo.domain.UserEntity;
import com.example.demo.domain.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserEntity insertUser(RegisterUserRequest request, String confirmPwd) {
        UserEntity checkId = userRepository.findById(request.getId());

        if (checkId != null) {
            throw new RuntimeException("이미 존재하는 회원 입니다.");
        }

        if (!request.getPassword().equals(confirmPwd)) {
            throw new RuntimeException("비밀번호가 서로 다릅니다.");
        }

        Encrypt hashPwd = new Encrypt();
        String changePwd = hashPwd.getEncrypt(request.getPassword(), hashPwd.salt);
        request.setPassword(changePwd);

        return userRepository.insertUser(request.toEntity());
    }
}
