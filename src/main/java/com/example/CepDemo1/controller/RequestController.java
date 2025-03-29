package com.example.CepDemo1.controller;

import com.example.CepDemo1.model.BeneficiaryModel;
import com.example.CepDemo1.model.DeviceModel;
import com.example.CepDemo1.model.RequestModel;
import com.example.CepDemo1.service.RequestService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/request")
@CrossOrigin(origins = "http://localhost:3000",  methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE})
public class RequestController {

    @Autowired
    private RequestService requestService;

    @PostMapping("/beneficiary/{beneficiaryId}")
    public ResponseEntity<RequestModel> createRequest(@PathVariable Long beneficiaryId, @Valid @RequestBody RequestModel requestModel){
        RequestModel requestedDevice = requestService.createRequest(beneficiaryId, requestModel);
        return ResponseEntity.ok(requestedDevice);

    }

    @GetMapping("/beneficiary/{beneficiaryId}")
    public ResponseEntity<List<RequestModel>> getRequestById(@PathVariable Long beneficiaryId){
        List<RequestModel> requestMade = requestService.getRequestByBeneficiaryId(beneficiaryId);
        return ResponseEntity.ok(requestMade);
    }

    @GetMapping("/donor/{donorId}")
    public ResponseEntity<List<RequestModel>> getRequestByDonorId(@PathVariable Long donorId){
        List<RequestModel> requestAccepted = requestService.getRequestByDonorId(donorId);
        return ResponseEntity.ok(requestAccepted);
    }


    @GetMapping("/pending")
    public ResponseEntity<List<RequestModel>> fetchRequest(){
        List<RequestModel> pendingRequests = requestService.fetchAllPendingRequests();
        return ResponseEntity.ok(pendingRequests);
    }

    @PostMapping("/{requestId}/donor/{donorId}")
    public ResponseEntity<RequestModel> acceptRequest(@PathVariable Long requestId, @PathVariable Long donorId){
        RequestModel acceptedRequest = requestService.acceptRequest(requestId,donorId);
        return ResponseEntity.ok(acceptedRequest);
    }

}

