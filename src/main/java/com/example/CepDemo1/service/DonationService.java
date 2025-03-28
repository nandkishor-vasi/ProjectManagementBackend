package com.example.CepDemo1.service;

import com.example.CepDemo1.model.DonorModel;
import com.example.CepDemo1.repo.DeviceRepo;
import com.example.CepDemo1.repo.DonationRepo;
import com.example.CepDemo1.repo.DonorRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DonationService {

    @Autowired
    DonationRepo donationRepo;

    @Autowired
    DeviceRepo deviceRepo;

    @Autowired
    DonorRepo donorRepo;

    private static final Logger logger = LoggerFactory.getLogger(DonationService.class);

    public List<Map<String, Object>> getTopDonors() {
        List<Object[]> results = donationRepo.findTopDonors();

        List<Map<String, Object>> leaderboard = new ArrayList<>();

        for (Object[] result : results) {
            Map<String, Object> map = new HashMap<>();
            map.put("donorId", result[0]);
            map.put("donorName", result[1]);
            map.put("totalDevicesDonated", result[2]);
            Map<String, Object> rank = getRank((Long) result[0]);
            map.put("rank", rank.get("rank"));
            leaderboard.add(map);
        }

        return leaderboard;
    }

    public Map<String, Object> getRank(Long userId){
        DonorModel donor = donorRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("Donor not found!"));
        List<Object[]> results = donationRepo.findTopDonors();
        int rank=1;

        for (Object[] result : results) {
            Long donorId = ((Number) result[0]).longValue();
            if (donorId.equals(userId)) {
                Map<String, Object> userRank = new HashMap<>();
                userRank.put("rank", rank);
                return userRank;
            }
            rank++;
        }

        return Map.of("message", "User not found in leaderboard");
    }
}
