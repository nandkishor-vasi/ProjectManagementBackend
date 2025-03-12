package com.example.CepDemo1.service;

import com.example.CepDemo1.model.StatsPost;
import com.example.CepDemo1.repo.StatsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class StatsService {

    @Autowired
    private final StatsRepo statsRepository;

    // Constructor injection
    public StatsService(StatsRepo statsRepository) {
        this.statsRepository = statsRepository;
    }

    // Fetch all stats
    public List<StatsPost> getAllStats() {
        return statsRepository.findAll();
    }
}
