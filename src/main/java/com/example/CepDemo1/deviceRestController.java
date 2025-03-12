package com.example.CepDemo1;

import com.example.CepDemo1.model.DevicePost;
import com.example.CepDemo1.model.StatsPost;
import com.example.CepDemo1.service.DeviceService;
import com.example.CepDemo1.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("http://localhost:3000/")
public class deviceRestController {

    @Autowired
    private DeviceService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private StatsService stasService;

    @GetMapping("api/devices")
    public List<DevicePost> getAllDevices(){
        return service.getAllDevices();
    }

    @GetMapping("api/stats")
    public List<StatsPost> getAllStats() {
        return stasService.getAllStats();
    }

    @GetMapping("api/load")
    public String loadData(){
        service.load();
        return "Success";
    }


}
