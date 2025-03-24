package com.example.CepDemo1.repo;

import com.example.CepDemo1.model.RequestModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestRepo extends JpaRepository<RequestModel, Long> {

    List<RequestModel> findByStatus(String pending);

    List<RequestModel> findByDonorId(Long donorId);

    List<RequestModel> findByBeneficiaryId(Long beneficiaryId);
}
