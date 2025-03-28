package com.example.CepDemo1.controller;

import com.example.CepDemo1.model.DonorModel;
import com.example.CepDemo1.service.DonationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/donation")
@CrossOrigin(origins = "http://localhost:3000")
public class DonationController {

    @Autowired
    DonationService donationService;

    @GetMapping("/leaderboard")
    public ResponseEntity<List<Map<String, Object>>> getLeaderBoard(){
        return ResponseEntity.ok(donationService.getTopDonors());
    }

    @GetMapping("/donors/{donorId}/leaderboard/rank")
    public ResponseEntity<Map<String, Object>> getRank(@PathVariable Long donorId){
        return ResponseEntity.ok(donationService.getRank(donorId));
    }
}
