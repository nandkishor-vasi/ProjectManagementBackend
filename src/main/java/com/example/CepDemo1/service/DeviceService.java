package com.example.CepDemo1.service;

import com.example.CepDemo1.model.DevicePost;
import com.example.CepDemo1.model.StatsPost;
import com.example.CepDemo1.repo.DeviceRepo;
import com.example.CepDemo1.repo.StatsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DeviceService {

    @Autowired
    public DeviceRepo repo;

    public List<DevicePost> getAllDevices() {
        return repo.findAll();
    }

    public void load() {
        List<DevicePost> jobs = new ArrayList<>(Arrays.asList(
                new DevicePost(1, "iPhone 15", "Apple", "Smartphone", "2023"),
                new DevicePost(2, "Galaxy S23", "Samsung", "Smartphone", "2023"),
                new DevicePost(3, "Pixel 8 Pro", "Google", "Smartphone", "2023"),
                new DevicePost(4, "MacBook Air M2", "Apple", "Laptop", "2022"),
                new DevicePost(5, "Surface Laptop 5", "Microsoft", "Laptop", "2022"),
                new DevicePost(6, "PlayStation 5", "Sony", "Gaming Console", "2020"),
                new DevicePost(7, "Xbox Series X", "Microsoft", "Gaming Console", "2020"),
                new DevicePost(8, "iPad Pro", "Apple", "Tablet", "2023"),
                new DevicePost(9, "Kindle Paperwhite", "Amazon", "E-Reader", "2021"),
                new DevicePost(10, "Galaxy Tab S8", "Samsung", "Tablet", "2022")
        ));
        repo.saveAll(jobs);
    }
}
