package com.example.CepDemo1.service;

import com.example.CepDemo1.model.BeneficiaryModel;
import com.example.CepDemo1.model.DeviceModel;
import com.example.CepDemo1.repo.BeneficiaryRepo;
import com.example.CepDemo1.repo.DeviceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BeneficiaryService {
    @Autowired
    private BeneficiaryRepo beneficiaryRepo;

    @Autowired
    private DeviceRepo deviceRepo;

    public BeneficiaryModel getBeneficiaryById(Long beneficiaryId){
        return beneficiaryRepo.findById(beneficiaryId)
                .orElseThrow(() -> new RuntimeException("Beneficiary not found with ID: " + beneficiaryId));
    }

    public BeneficiaryModel updateImageUrl(BeneficiaryModel beneficiary) {
        return beneficiaryRepo.save(beneficiary);
    }

    public BeneficiaryModel updateProfile(BeneficiaryModel beneficiary) {
        return beneficiaryRepo.save(beneficiary);
    }
}
