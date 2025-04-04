package com.example.CepDemo1.repo;

import com.example.CepDemo1.model.AdminModel;
import com.example.CepDemo1.model.MemberModel;
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
public class MemberRepo {

    @Autowired
    private UserRepo userRepo;

    private JdbcTemplate jdbcTemplate;

    public MemberRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public MemberModel save(MemberModel member) {
        String sql = "INSERT INTO members (user_id, profile_picture, address, is_active, created_at, last_login) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setLong(1, member.getUser().getId()); // Foreign key reference to user
            ps.setString(2, member.getProfilePicture());
            ps.setString(3, member.getAddress());
            ps.setBoolean(4, member.isActive());
            ps.setTimestamp(5, member.getCreatedAt() != null ? new Timestamp(member.getCreatedAt().getTime()) : new Timestamp(System.currentTimeMillis()));
            ps.setTimestamp(6, member.getLastLogin() != null ? new java.sql.Timestamp(member.getLastLogin().getTime()) : null);
            return ps;
        }, keyHolder);

        System.out.println("Generated Keys: " + keyHolder.getKeys());

        Map<String, Object> keys = keyHolder.getKeys();
        Long generatedId = ((Number) Objects.requireNonNull(keys).get("id")).longValue();

        member.setId(generatedId);

        return member;
    }

    public MemberModel findById(Long memberId) {
        MemberModel member = null;
        String sql = "SELECT * FROM members WHERE user_id = ?";

        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{memberId}, memberRowMapper());
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    private RowMapper<MemberModel> memberRowMapper(){
        return (rs, rowNum) -> {
            MemberModel member = new MemberModel();
            member.setId(rs.getLong("id"));
            member.setProfilePicture(rs.getString("profile_picture"));
            member.setAddress(rs.getString("address"));
            member.setActive(rs.getBoolean("is_active"));
            member.setCreatedAt(rs.getTimestamp("created_at"));

            Long userId = rs.getLong("user_id");
            userRepo.findById(userId).ifPresent(member::setUser);

            return member;
        };
    }

}
