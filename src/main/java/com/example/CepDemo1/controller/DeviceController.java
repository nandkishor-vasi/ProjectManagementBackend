package com.example.CepDemo1.controller;

import com.example.CepDemo1.model.DeviceModel;
import com.example.CepDemo1.repo.DeviceRepo;
import com.example.CepDemo1.service.DeviceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/devices")
@CrossOrigin(origins = "http://localhost:3000")
public class DeviceController {
    @Autowired
    private DeviceService deviceService;

    @PostMapping("/donors/{donorId}")
    public ResponseEntity<DeviceModel> donateDevice(@PathVariable Long donorId, @Valid @RequestBody DeviceModel deviceModel) {
        DeviceModel savedDevice = deviceService.donateDevice(donorId, deviceModel);

        return ResponseEntity.ok(savedDevice);
    }

    @GetMapping("/donors/{donorId}")
    public ResponseEntity<List<DeviceModel>> getDeviceByDonorId(@PathVariable Long donorId) {
        List<DeviceModel> devices = deviceService.getDevicesByDonorId(donorId);
        return ResponseEntity.ok(devices);
    }

    @PutMapping("/{deviceId}/beneficiaries/{beneficiaryId}")
    public ResponseEntity<DeviceModel> acceptDevice(@PathVariable Long deviceId, @PathVariable Long beneficiaryId){
        DeviceModel acceptedDevice = deviceService.acceptDevice(deviceId,beneficiaryId);
        return ResponseEntity.ok(acceptedDevice);
    }

    @GetMapping("/available")
    public ResponseEntity<List<DeviceModel>> getAvailableDevices(){
        List<DeviceModel> avialableDevices = deviceService.getAvailableDevices();
        return ResponseEntity.ok(avialableDevices);
    }
}
