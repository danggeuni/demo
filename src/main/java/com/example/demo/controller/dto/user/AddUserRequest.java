package com.example.demo.controller.dto.user;

import com.example.demo.domain.user.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class AddUserRequest {
    private String name;
    private String address;
    private String phone;

    public UserEntity toEntity(){
        return new UserEntity(null, name, address, phone);
    }
}
