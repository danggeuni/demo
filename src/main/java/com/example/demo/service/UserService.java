package com.example.demo.service;

import com.example.demo.util.EncryptionUtil;
import com.example.demo.controller.dto.user.LoginUserRequest;
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
            throw new RuntimeException("이미 등록된 아이디 입니다.");
        }

        if (!request.getPassword().equals(confirmPwd)) {
            throw new RuntimeException("비밀번호가 서로 다릅니다.");
        }

        EncryptionUtil hashPwd = new EncryptionUtil();
        String changePwd = hashPwd.getEncrypt(request.getPassword(), hashPwd.salt);
        request.setPassword(changePwd);

        return userRepository.insertUser(request.toEntity());
    }

    public void loginUser(LoginUserRequest request){
        UserEntity existUser = userRepository.findById(request.getId());

        EncryptionUtil pwd = new EncryptionUtil();
        String password = pwd.getEncrypt(request.getPassword(), pwd.salt);

        if(existUser == null){
            throw new RuntimeException("존재하지 않는 회원입니다.");
        }

        if(!existUser.getPassword().equals(password)){
            throw new RuntimeException("비밀번호가 틀립니다.");
        }
    }
}
