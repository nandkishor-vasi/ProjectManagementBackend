package com.example.CepDemo1.service;

import com.example.CepDemo1.model.MemberModel;
import com.example.CepDemo1.repo.MemberRepo;
import com.example.CepDemo1.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberService {
    @Autowired
    private MemberRepo memberRepo;

    @Autowired
    private UserRepo userRepo;


    public MemberModel getMemberById(Long memberId) {
        return memberRepo.findById(memberId);
    }
}
