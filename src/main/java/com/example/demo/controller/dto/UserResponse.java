package com.example.demo.controller.dto;


import com.example.demo.domain.user.UserEntity;
import lombok.Getter;

@Getter
public class UserResponse {

    private final Long id;
    private final String name;
    private final String address;
    private final String phone;

    public UserResponse(UserEntity entity) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.address = entity.getAddress();
        this.phone = entity.getPhone();
    }
}
