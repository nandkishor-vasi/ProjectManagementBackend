package com.example.CepDemo1.controller;

import com.example.CepDemo1.model.BeneficiaryModel;
import com.example.CepDemo1.service.BeneficiaryService;
import com.example.CepDemo1.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/beneficiary")
@CrossOrigin(origins = "http://localhost:3000")
public class BeneficiaryController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private BeneficiaryService beneficiaryService;
    
    @PreAuthorize("hasRole('BENEFICIARY')")
    @GetMapping("/{beneficiaryId}")
    public ResponseEntity<BeneficiaryModel> getBeneficiary(@PathVariable Long beneficiaryId, @RequestHeader("Authorization") String token){
        String username = jwtService.extractUserName(token.substring(7));
        BeneficiaryModel beneficiary = beneficiaryService.getDonorById(beneficiaryId);
        return ResponseEntity.ok(beneficiary);
    }

}
