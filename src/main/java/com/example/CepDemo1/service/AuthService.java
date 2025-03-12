package com.example.CepDemo1.service;//package com.example.CepDemo1.service;
//
//import com.example.CepDemo1.model.UserModel;
//import com.example.CepDemo1.repo.UserRepo;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AuthService {
//    @Autowired
//    private UserRepo userRepo;
//
//    @Autowired
//    private PasswordEncoder passwordEncoder;
//
//    public UserModel registerUser(UserModel user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        return userRepo.save(user);
//    }
//
//    public UserModel loginUser(String email, String password) {
//        UserModel user = userRepo.findByEmail(email)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//        System.out.println("User found: " + user.getEmail());
//        System.out.println("Stored password: " + user.getPassword());
//        System.out.println("Input password: " + password);
//
//        if (!passwordEncoder.matches(password, user.getPassword())) {
//            throw new RuntimeException("Invalid password");
//        }
//        return user;
//    }
//}
