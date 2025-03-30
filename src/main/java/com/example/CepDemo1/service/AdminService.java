package com.example.CepDemo1.service;

import com.example.CepDemo1.model.AdminModel;
import com.example.CepDemo1.repo.AdminRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

@Service
public class AdminService {
    @Autowired
    private AdminRepo adminRepo;


    public AdminModel getAdminById(Long adminId) {
        return adminRepo.findById(adminId);
    }
}
