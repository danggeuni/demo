package com.example.demo.controller.dto.user;

import com.example.demo.domain.UserEntity;
import lombok.Data;

@Data
public class LoginUserRequest {
    private String id;
    private String password;

    public UserEntity toEntity(){
        return new UserEntity(id, null, password);
    }
}
