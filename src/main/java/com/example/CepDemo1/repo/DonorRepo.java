package com.example.CepDemo1.repo;

import com.example.CepDemo1.model.DonorModel;
import jdk.jfr.Registered;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DonorRepo extends JpaRepository<DonorModel, Long> {
    
}
