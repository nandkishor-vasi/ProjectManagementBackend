package com.example.CepDemo1.service;

import com.example.CepDemo1.model.BeneficiaryModel;
import com.example.CepDemo1.repo.BeneficiaryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BeneficiaryService {
    @Autowired
    private BeneficiaryRepo beneficiaryRepo;

    public BeneficiaryModel getDonorById(Long beneficiaryId){
        return beneficiaryRepo.findById(beneficiaryId)
                .orElseThrow(() -> new RuntimeException("Beneficiary not found with ID: " + beneficiaryId));
    }
}
