package com.example.CepDemo1.repo;

import com.example.CepDemo1.model.AdminModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Objects;

@Repository
public class AdminRepo {

    private JdbcTemplate jdbcTemplate;

    public AdminRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public AdminModel save(AdminModel admin) {
        String sql = "INSERT INTO admin (user_id, city, state, country, profile_image_url, designation, created_at, updated_at, status) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update( connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, admin.getUser().getId());
            ps.setString(2, admin.getCity());
            ps.setString(3, admin.getState());
            ps.setString(4, admin.getCountry());
            ps.setString(5, admin.getProfileImageUrl());
            ps.setString(6, admin.getDesignation());
            ps.setTimestamp(7, new java.sql.Timestamp(admin.getCreatedAt().getTime()));
            ps.setTimestamp(8, new java.sql.Timestamp(admin.getUpdatedAt().getTime()));
            ps.setString(9, admin.getStatus().toString());
            return ps;

        }, keyHolder);

        Long generatedId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        admin.setId(generatedId);

        return admin;
    }
}
