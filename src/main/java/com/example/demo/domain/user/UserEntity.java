package com.example.demo.domain.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserEntity {
    private final Long id;
    private final String name;
    private final String address;
    private final String phone;
}
