package com.example.CepDemo1.service;

import com.example.CepDemo1.model.BeneficiaryModel;
import com.example.CepDemo1.model.DonorModel;
import com.example.CepDemo1.model.UserModel;
import com.example.CepDemo1.repo.BeneficiaryRepo;
import com.example.CepDemo1.repo.DonorRepo;
import com.example.CepDemo1.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Autowired
    private DonorRepo donorRepo;

    @Autowired
    private BeneficiaryRepo beneficiaryRepo;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

    public UserModel saveUser(UserModel user) {
        user.setPassword(encoder.encode(user.getPassword()));
        UserModel savedUser =  repo.save(user);

        if(user.getRole() == UserModel.Role.DONOR){
            DonorModel donor = new DonorModel();
            donor.setUser(user);
            donorRepo.save(donor);
        } else if (user.getRole()==UserModel.Role.BENEFICIARY){
            BeneficiaryModel beneficiary = new BeneficiaryModel();
            beneficiary.setUser(user);
            beneficiaryRepo.save(beneficiary);
        }

        return savedUser;
    }

    public List<UserModel> getAllUsers() {
        return repo.findAll();
    }

    public UserModel getUserByUsername(String username) {
        return repo.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found: " + username));
    }

}
