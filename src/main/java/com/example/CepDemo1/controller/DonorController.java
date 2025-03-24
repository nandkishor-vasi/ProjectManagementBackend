package com.example.CepDemo1.controller;

import com.example.CepDemo1.model.DonorModel;
import com.example.CepDemo1.model.RequestModel;
import com.example.CepDemo1.service.DonorService;
import com.example.CepDemo1.service.JwtService;
import com.example.CepDemo1.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.Repository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/donors")
@CrossOrigin(origins = "http://localhost:3000")
public class DonorController {
    @Autowired
    private DonorService donorService;

    @Autowired
    private RequestService requestService;

    @Autowired
    private JwtService jwtService;

    @PreAuthorize("hasRole('DONOR')")
    @GetMapping("/{donorId}")
    public ResponseEntity<DonorModel> getDonorById(@PathVariable Long donorId, @RequestHeader("Authorization") String token){
        String username = jwtService.extractUserName(token.substring(7));
        DonorModel donor = donorService.getDonorById(donorId);
        return  ResponseEntity.ok(donor);
    }

    @PreAuthorize("hasRole('DONOR')")
    @GetMapping("/{donorId}/history")
    public ResponseEntity<List<RequestModel>> getRequestByDonorId(@PathVariable Long donorId){
        DonorModel donor = donorService.getDonorById(donorId);
        List<RequestModel> requests =  requestService.getRequestByDonorId(donorId);
        return ResponseEntity.ok(requests);
    }

    @PreAuthorize("hasRole('DONOR')")
    @PutMapping("/{donorId}/updateImage")
    public ResponseEntity<DonorModel> updateImageUrl(@PathVariable Long donorId, @RequestBody Map<String, String> request){
        DonorModel donor = donorService.getDonorById(donorId);
        if (donor == null) {
            return ResponseEntity.notFound().build();
        }
        donor.setProfileImageUrl(request.get("profileImageUrl"));
        DonorModel updatedDonor = donorService.updateImageUrl(donor);
        return ResponseEntity.ok(updatedDonor);
    }

    @PutMapping("/{donorId}/profile")
    public ResponseEntity<DonorModel> updateProfile(@PathVariable Long donorId, @RequestBody DonorModel updatedDonor){
        DonorModel donor = donorService.getDonorById(donorId);
        if (donor == null) {
            return ResponseEntity.notFound().build();
        }

        if (updatedDonor.getCity() != null) donor.setCity(updatedDonor.getCity());
        if (updatedDonor.getState() != null) donor.setState(updatedDonor.getState());
        if (updatedDonor.getCountry() != null) donor.setCountry(updatedDonor.getCountry());
        if(updatedDonor.getNotes() !=null) donor.setNotes(updatedDonor.getNotes());
        if(updatedDonor.getDonorType() != null) donor.setDonorType(updatedDonor.getDonorType());

        DonorModel savedDonor = donorService.updateDonor(donor);

        return ResponseEntity.ok(savedDonor);
    }
}
