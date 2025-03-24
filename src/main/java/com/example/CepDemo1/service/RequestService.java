package com.example.CepDemo1.service;

import com.example.CepDemo1.model.BeneficiaryModel;
import com.example.CepDemo1.model.DonorModel;
import com.example.CepDemo1.model.RequestModel;
import com.example.CepDemo1.repo.BeneficiaryRepo;
import com.example.CepDemo1.repo.DonorRepo;
import com.example.CepDemo1.repo.RequestRepo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;

@Service
public class RequestService {

    @Autowired
    private RequestRepo requestRepo;

    @Autowired
    private DonorRepo donorRepo;

    @Autowired
    private BeneficiaryRepo beneficiaryRepo;
    public RequestModel createRequest(Long beneficiaryId, RequestModel requestModel) {
        BeneficiaryModel beneficiary = beneficiaryRepo.findById(beneficiaryId)
                .orElseThrow(() -> new RuntimeException("Beneficiary not found!"));
        requestModel.setBeneficiary(beneficiary);
        requestModel.setBeneficiaryName(beneficiary.getUser().getName());
        requestModel.setCreatedAt(new Date());

        if (requestModel.getDeviceName() == null || requestModel.getDeviceName().isEmpty()) {
            throw new RuntimeException("Device name is required!");
        }

        return requestRepo.save(requestModel);

    }

    public List<RequestModel> fetchAllPendingRequests() {
        return requestRepo.findByStatus("Pending");

    }

    public RequestModel acceptRequest(Long requestId, Long donorId) {
        Optional<RequestModel> requestAcquired = requestRepo.findById(requestId);
        Optional<DonorModel> donorAcquired = donorRepo.findById(donorId);

        if(requestAcquired.isPresent() && donorAcquired.isPresent()){
            RequestModel request = requestAcquired.get();
            request.setDonor(donorAcquired.get());
            request.setStatus("Accepted");

            Date now = new Date();
            SimpleDateFormat istFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            istFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));

            String formattedDate = istFormat.format(now);
            System.out.println("Accepted Request ID: " + request.getId() + " | Accepted Date: " + formattedDate);

            request.setAcceptedAt(now);
            return requestRepo.save(request);
        } else {
            throw new RuntimeException("Request or Donor not found");
        }
    }

    public List<RequestModel> getRequestByDonorId(Long donorId) {
        DonorModel donor = donorRepo.findById(donorId)
                .orElseThrow(() -> new RuntimeException("Donor not found!"));
        return requestRepo.findByDonorId(donorId);
    }

    public List<RequestModel> getRequestByBeneficiaryId(Long beneficiaryId) {
        BeneficiaryModel beneficiary = beneficiaryRepo.findById(beneficiaryId)
                .orElseThrow(() -> new RuntimeException("Beneficiary not found!"));

        return requestRepo.findByBeneficiaryId(beneficiaryId);
    }
}
