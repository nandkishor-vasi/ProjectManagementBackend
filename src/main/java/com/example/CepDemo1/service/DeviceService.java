package com.example.CepDemo1.service;

import com.example.CepDemo1.model.BeneficiaryModel;
import com.example.CepDemo1.model.DeviceModel;
import com.example.CepDemo1.model.DonationModel;
import com.example.CepDemo1.model.DonorModel;
import com.example.CepDemo1.repo.BeneficiaryRepo;
import com.example.CepDemo1.repo.DeviceRepo;
import com.example.CepDemo1.repo.DonationRepo;
import com.example.CepDemo1.repo.DonorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepo deviceRepo;

    @Autowired
    private DonorRepo donorRepo;

    @Autowired
    private BeneficiaryRepo beneficiaryRepo;

    @Autowired
    private DonationRepo donationRepo;

    public List<DeviceModel> getDevicesByDonorId(Long donorId){
        return deviceRepo.findByDonorId(donorId);
    }

    public DeviceModel donateDevice(Long DonorId, DeviceModel deviceModel) {
        DonorModel donor = donorRepo.findById(DonorId)
                .orElseThrow(() -> new RuntimeException("Donor not found!"));
        deviceModel.setDonor(donor);
//        deviceModel.setDeviceImageUrl(imageUrl.get("deviceImageUrl"));
        deviceModel.setDonorName(donor.getUser().getName());
        DeviceModel savedDevice = deviceRepo.save(deviceModel);

        DonationModel donation = new DonationModel();
        donation.setDonor(donor);
        donation.setDevice(savedDevice);
        donation.setDonationAcceptedDate(new Date());

        donationRepo.save(donation);

        return savedDevice;
    }

    public List<DeviceModel> getDevicesByBeneficiaryId(Long beneficiaryId) {
        return deviceRepo.findByBeneficiaryId(beneficiaryId);
    }

    public DeviceModel acceptDevice(Long deviceId, Long beneficiaryId) {
        Optional<DeviceModel> deviceGot = deviceRepo.findById(deviceId);
        Optional<BeneficiaryModel> beneficiary = beneficiaryRepo.findById(beneficiaryId);

        if(deviceGot.isPresent() && beneficiary.isPresent()){
            DeviceModel device = deviceGot.get();
            device.setBeneficiary(beneficiary.get());
            device.setStatus("Accepted");

            Date now = new Date();
            SimpleDateFormat istFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            istFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));

            String formattedDate = istFormat.format(now);
            System.out.println("Accepted Device ID: " + device.getId() + " | Accepted Date: " + formattedDate);

            device.setAcceptedDate(now);

            return deviceRepo.save(device);
        } else {
            throw new RuntimeException("Device or Beneficiary Not Found");
        }
    }

    public List<DeviceModel> getAvailableDevices() {
        return deviceRepo.findByStatus("Pending");
    }
}
