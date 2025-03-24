package com.example.CepDemo1.service;

import com.example.CepDemo1.model.DonorModel;
import com.example.CepDemo1.repo.DonorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonorService {
    @Autowired
    private DonorRepo donorRepo;

    public DonorModel getDonorById(long donorId){
        return donorRepo.findById(donorId)
                .orElseThrow(() -> new RuntimeException("Donor not found with ID: " + donorId));
    }

    public DonorModel updateImageUrl(DonorModel donor) {
        return donorRepo.save(donor);
    }

    public DonorModel updateDonor(DonorModel donor) {
        return donorRepo.save(donor);
    }
}
