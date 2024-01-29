package com.example.demo.dto;

import com.example.demo.domain.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class MemberDTO {

    private Long id;
    private String name;
    private String address;
    private String phone;

    public MemberEntity toEntity(){
        return MemberEntity.builder()
                .name(name)
                .address(address)
                .phone(phone)
                .build();
    }
}
