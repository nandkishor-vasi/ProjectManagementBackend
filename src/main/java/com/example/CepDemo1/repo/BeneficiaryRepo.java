package com.example.CepDemo1.repo;

import com.example.CepDemo1.model.BeneficiaryModel;
import com.example.CepDemo1.model.DeviceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BeneficiaryRepo extends JpaRepository<BeneficiaryModel, Long> {

}
