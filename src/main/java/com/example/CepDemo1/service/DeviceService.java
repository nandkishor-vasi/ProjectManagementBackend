package com.example.CepDemo1.service;

import com.example.CepDemo1.model.BeneficiaryModel;
import com.example.CepDemo1.model.DeviceModel;
import com.example.CepDemo1.model.DonorModel;
import com.example.CepDemo1.repo.BeneficiaryRepo;
import com.example.CepDemo1.repo.DeviceRepo;
import com.example.CepDemo1.repo.DonorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepo deviceRepo;

    @Autowired
    private DonorRepo donorRepo;

    @Autowired
    private BeneficiaryRepo beneficiaryRepo;

    public List<DeviceModel> getDevicesByDonorId(Long donorId){
        return deviceRepo.findByDonorId(donorId);
    }

    public DeviceModel donateDevice(Long DonorId, DeviceModel deviceModel) {
        DonorModel donor = donorRepo.findById(DonorId)
                .orElseThrow(() -> new RuntimeException("Donor not found!"));
        deviceModel.setDonor(donor);
        return deviceRepo.save(deviceModel);
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
            device.setAcceptedDate(new Date());
            return deviceRepo.save(device);
        } else {
            throw new RuntimeException("Device or Beneficiary Not Found");
        }
    }

    public List<DeviceModel> getAvailableDevices() {
        return deviceRepo.findByStatus("Pending");
    }
}
