package com.example.CepDemo1.repo;

import com.example.CepDemo1.model.AdminModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.Map;
import java.util.Objects;

@Repository
public class AdminRepo {

    @Autowired
    private UserRepo userRepo;

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
            ps.setTimestamp(7, admin.getCreatedAt() != null ? new Timestamp(admin.getCreatedAt().getTime()) : new Timestamp(System.currentTimeMillis()));
            ps.setTimestamp(8, admin.getUpdatedAt() != null ? new Timestamp(admin.getUpdatedAt().getTime()) : new Timestamp(System.currentTimeMillis()));
            ps.setString(9, admin.getStatus().toString());
            return ps;

        }, keyHolder);

        System.out.println("Generated Keys: " + keyHolder.getKeys());

        Map<String, Object> keys = keyHolder.getKeys();
        Long generatedId = ((Number) Objects.requireNonNull(keys).get("id")).longValue();

        admin.setId(generatedId);

        return admin;
    }

    public AdminModel findById(Long adminId) {
        AdminModel admin = null;
        String sql = "SELECT * FROM admin WHERE user_id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{adminId}, adminRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private RowMapper<AdminModel> adminRowMapper() {
        return (rs, rowNum) -> {
            AdminModel admin = new AdminModel();
            admin.setId(rs.getLong("id"));
            admin.setCity(rs.getString("city"));
            admin.setState(rs.getString("state"));
            admin.setCountry(rs.getString("country"));
            admin.setProfileImageUrl(rs.getString("profile_image_url"));
            admin.setDesignation(rs.getString("designation"));
            admin.setCreatedAt(rs.getTimestamp("created_at"));
            admin.setUpdatedAt(rs.getTimestamp("updated_at"));
            admin.setStatus(AdminModel.Status.valueOf(rs.getString("status")));

            Long userId = rs.getLong("user_id");
            userRepo.findById(userId).ifPresent(admin::setUser);

            return admin;
        };
    }

}
