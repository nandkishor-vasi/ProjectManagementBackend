package com.example.CepDemo1.controller;

import com.example.CepDemo1.model.DeviceModel;
import com.example.CepDemo1.repo.DeviceRepo;
import com.example.CepDemo1.service.DeviceService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        if (devices.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(devices);
    }
}
