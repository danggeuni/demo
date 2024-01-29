package com.example.demo.service;

import com.example.demo.domain.MemberEntity;
import com.example.demo.dto.MemberDTO;
import com.example.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberEntity save(MemberDTO memberDTO){
        return memberRepository.save(memberDTO.toEntity());
    }
}
