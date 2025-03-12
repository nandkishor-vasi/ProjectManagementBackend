package com.example.CepDemo1.repo;

import com.example.CepDemo1.model.BeneficiaryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeneficiaryRepo extends JpaRepository<BeneficiaryModel, Long> {

}
