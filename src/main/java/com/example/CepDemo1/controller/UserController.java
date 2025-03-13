package com.example.CepDemo1.controller;

import com.example.CepDemo1.model.UserModel;
//import com.example.CepDemo1.service.AuthService;
import com.example.CepDemo1.service.JwtService;
import com.example.CepDemo1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/auth")
public class UserController {

//    @Autowired
//    private AuthService authService;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;


    @PostMapping("/signup")
    public UserModel signup(@RequestBody UserModel user) {
        return userService.saveUser(user);
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody UserModel user) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

        if(authentication.isAuthenticated()){
            String token = jwtService.generateToken(user.getUsername());
            UserModel loggedInUser = userService.getUserByUsername(user.getUsername());

            Map<String, Object> response = new HashMap<>();
            response.put("username", loggedInUser.getUsername());
            response.put("role", loggedInUser.getRole());
            response.put("token", token);
            response.put("id", loggedInUser.getId());
            return  response;

        } else {
            throw new RuntimeException("Authentication Failed");
        }
    }
    @GetMapping("/users")
    public List<UserModel> getAllUsers() {
        return userService.getAllUsers();
    }

    

}