package com.example.CepDemo1.controller;

import com.example.CepDemo1.model.MemberModel;
import com.example.CepDemo1.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @GetMapping("/{memberId}")
    public MemberModel getMemberById(@PathVariable Long memberId){
        return memberService.getMemberById(memberId);
    }
}
