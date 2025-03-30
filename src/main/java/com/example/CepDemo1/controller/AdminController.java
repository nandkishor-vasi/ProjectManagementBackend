package com.example.CepDemo1.controller;

import com.example.CepDemo1.model.AdminModel;
import com.example.CepDemo1.repo.AdminRepo;
import com.example.CepDemo1.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;
    @GetMapping("/{adminId}")
    public AdminModel getById(@PathVariable Long adminId){
        return adminService.getAdminById(adminId);
    }
}
