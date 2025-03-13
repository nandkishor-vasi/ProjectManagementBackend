package com.example.CepDemo1.service;

import com.example.CepDemo1.model.DeviceModel;
import com.example.CepDemo1.model.DonorModel;
import com.example.CepDemo1.repo.DeviceRepo;
import com.example.CepDemo1.repo.DonorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepo deviceRepo;

    @Autowired
    private DonorRepo donorRepo;

    public List<DeviceModel> getDevicesByDonorId(Long donorId){
        return deviceRepo.findByDonorId(donorId);
    }

    public DeviceModel donateDevice(Long DonorId, DeviceModel deviceModel) {
        DonorModel donor = donorRepo.findById(DonorId)
                .orElseThrow(() -> new RuntimeException("Donor not found!"));
        deviceModel.setDonor(donor);
        return deviceRepo.save(deviceModel);
    }
}
