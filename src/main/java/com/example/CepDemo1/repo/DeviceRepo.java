package com.example.CepDemo1.repo;

import com.example.CepDemo1.model.DeviceModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepo extends JpaRepository<DeviceModel, Long> {
    List<DeviceModel> findByDonorId(Long donorId);
    List<DeviceModel> findByBeneficiaryId(Long beneficiaryId);

    List<DeviceModel> findByStatus(String pending);
}
