package com.example.CepDemo1.repo;

import com.example.CepDemo1.model.DevicePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.support.JpaRepositoryConfigurationAware;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepo extends JpaRepository<DevicePost, Integer> {

}
