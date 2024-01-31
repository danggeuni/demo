package com.example.demo.domain.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
public class UserEntity {

    private final Long id;
    private final String name;
    private final String address;
    private final String phone;

    public UserEntity(Long id, String name, String address, String phone){
        this.id = null;
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
}
