package com.example.CepDemo1.repo;

import com.example.CepDemo1.model.DonationModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DonationRepo extends JpaRepository<DonationModel, Long> {
    @Query("SELECT d.donor.id, d.donor.user.name, COUNT(d.device.id) AS totalDevices " +
            "FROM DonationModel d " +
            "GROUP BY d.donor.id, d.donor.user.name " +
            "ORDER BY totalDevices DESC")
    List<Object[]> findTopDonors();

}
