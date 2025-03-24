package com.example.CepDemo1.controller;

import com.example.CepDemo1.model.BeneficiaryModel;
import com.example.CepDemo1.model.DeviceModel;
import com.example.CepDemo1.model.DonorModel;
import com.example.CepDemo1.service.BeneficiaryService;
import com.example.CepDemo1.service.DeviceService;
import com.example.CepDemo1.service.JwtService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/beneficiary")
@CrossOrigin(origins = "http://localhost:3000")
public class BeneficiaryController {
    @Autowired
    private JwtService jwtService;

    @Autowired
    private BeneficiaryService beneficiaryService;

    @Autowired
    private DeviceService deviceService;
    
    @PreAuthorize("hasRole('BENEFICIARY')")
    @GetMapping("/{beneficiaryId}")
    public ResponseEntity<BeneficiaryModel> getBeneficiary(@PathVariable Long beneficiaryId, @RequestHeader("Authorization") String token){
        String username = jwtService.extractUserName(token.substring(7));
        BeneficiaryModel beneficiary = beneficiaryService.getBeneficiaryById(beneficiaryId);
        return ResponseEntity.ok(beneficiary);
    }

    @PreAuthorize("hasRole('BENEFICIARY')")
    @GetMapping("/{beneficiaryId}/history")
    public ResponseEntity<List<DeviceModel>> getReceivedDevices(@PathVariable long beneficiaryId, @RequestHeader("Authorization") String token){
        String username = jwtService.extractUserName(token.substring(7));
        BeneficiaryModel beneficiary = beneficiaryService.getBeneficiaryById(beneficiaryId);

        List<DeviceModel> receivedDevices = deviceService.getDevicesByBeneficiaryId(beneficiaryId);
        return ResponseEntity.ok(receivedDevices);
    }

    @PreAuthorize("hasRole('BENEFICIARY')")
    @PutMapping("/{beneficiaryId}/updateImage")
    public ResponseEntity<BeneficiaryModel> updateImageUrl(@PathVariable Long beneficiaryId, @RequestBody Map<String, String> request){
        BeneficiaryModel beneficiary = beneficiaryService.getBeneficiaryById(beneficiaryId);
        if (beneficiary == null) {
            return ResponseEntity.notFound().build();
        }
        beneficiary.setProfileImageUrl(request.get("profileImageUrl"));
        BeneficiaryModel updatedBeneficiary = beneficiaryService.updateImageUrl(beneficiary);
        return ResponseEntity.ok(updatedBeneficiary);
    }

    @PreAuthorize("hasRole('BENEFICIARY')")
    @PutMapping("/{beneficiaryId}/profile")
    public ResponseEntity<BeneficiaryModel> updateProfile(@PathVariable Long beneficiaryId, @RequestBody BeneficiaryModel updatedBeneficiary){
        BeneficiaryModel beneficiary = beneficiaryService.getBeneficiaryById(beneficiaryId);
        if (beneficiary == null) {
            return ResponseEntity.notFound().build();
        }


        if (updatedBeneficiary.getCity() != null) beneficiary.setCity(updatedBeneficiary.getCity());
        if (updatedBeneficiary.getState() != null) beneficiary.setState(updatedBeneficiary.getState());
        if (updatedBeneficiary.getCountry() != null) beneficiary.setCountry(updatedBeneficiary.getCountry());
        if (updatedBeneficiary.getStatus() != null) beneficiary.setStatus(updatedBeneficiary.getStatus());
        if (updatedBeneficiary.getNeedDescription() != null) beneficiary.setNeedDescription(updatedBeneficiary.getNeedDescription());

        BeneficiaryModel savedBeneficiary = beneficiaryService.updateProfile(beneficiary);
        return ResponseEntity.ok(savedBeneficiary);
    }

}
