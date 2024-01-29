package com.example.demo.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@Table (name = "members")
public class MemberEntity {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (name = "name", nullable = false)
    private String name;

    @Column
    private String address;

    @Column
    private String phone;

    @Builder
    public MemberEntity(String name, String address, String phone){
        this.name = name;
        this.address = address;
        this.phone = phone;
    }
}
