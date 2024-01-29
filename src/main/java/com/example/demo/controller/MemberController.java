package com.example.demo.controller;


import com.example.demo.domain.MemberEntity;
import com.example.demo.dto.MemberDTO;
import com.example.demo.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/api/members")
    public ResponseEntity<MemberEntity> saveMember(@RequestBody MemberDTO memberDTO){
        MemberEntity member = memberService.save(memberDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(member);
    }
}
