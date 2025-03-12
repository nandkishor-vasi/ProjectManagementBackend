package com.example.CepDemo1.repo;

import com.example.CepDemo1.model.StatsPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatsRepo extends JpaRepository<StatsPost, Integer> {

}
