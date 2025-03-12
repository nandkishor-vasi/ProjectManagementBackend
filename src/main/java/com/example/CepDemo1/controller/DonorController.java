package com.example.CepDemo1.controller;

import com.example.CepDemo1.model.DonorModel;
import com.example.CepDemo1.service.DonorService;
import com.example.CepDemo1.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/donors")
public class DonorController {
    @Autowired
    private DonorService donorService;

    @Autowired
    private JwtService jwtService;

    @PreAuthorize("hasRole('DONOR')")
    @GetMapping("/{donorId}")
    public ResponseEntity<DonorModel> getDonorById(@PathVariable Long donorId, @RequestHeader("Authorization") String token){
        String username = jwtService.extractUserName(token.substring(7));
        DonorModel donor = donorService.getDonorById(donorId);
        return  ResponseEntity.ok(donor);
    }
}
