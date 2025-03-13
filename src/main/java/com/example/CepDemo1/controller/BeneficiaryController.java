package com.example.CepDemo1.controller;

import com.example.CepDemo1.model.BeneficiaryModel;
import com.example.CepDemo1.model.DeviceModel;
import com.example.CepDemo1.service.BeneficiaryService;
import com.example.CepDemo1.service.DeviceService;
import com.example.CepDemo1.service.JwtService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

}
