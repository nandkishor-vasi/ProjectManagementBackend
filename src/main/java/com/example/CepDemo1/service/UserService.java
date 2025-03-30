package com.example.CepDemo1.service;

import com.example.CepDemo1.model.AdminModel;
import com.example.CepDemo1.model.MemberModel;
import com.example.CepDemo1.model.Role;
import com.example.CepDemo1.model.UserModel;
import com.example.CepDemo1.repo.AdminRepo;
import com.example.CepDemo1.repo.MemberRepo;
import com.example.CepDemo1.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private AdminRepo adminRepo;

    @Autowired
    private MemberRepo memberRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserModel saveUser(UserModel user) {
        user.setPassword(encoder.encode(user.getPassword()));
        UserModel savedUser =  repo.save(user);

        if(user.getRole() == Role.MEMBER){
            MemberModel member = new MemberModel();
            member.setUser(user);
            memberRepo.save(member);
        } else if (user.getRole()==Role.ADMIN){
            AdminModel admin = new AdminModel();
            admin.setUser(user);
            adminRepo.save(admin);
        }

        return savedUser;
    }

    public UserModel getUserByUsername(String username) {
        return repo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }

}
