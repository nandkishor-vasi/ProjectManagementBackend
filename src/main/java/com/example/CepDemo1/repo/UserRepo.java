package com.example.CepDemo1.repo;

import com.example.CepDemo1.model.Role;
import com.example.CepDemo1.model.UserModel;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@Repository
public class UserRepo {
    private final JdbcTemplate jdbcTemplate;

    public UserRepo(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public UserModel save(UserModel user) {
        String sql = "INSERT INTO users (name, email, username, password, phone_number, address, role) VALUES (?, ?, ?, ?, ?, ?, ?)";

        KeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, user.getName());
            ps.setString(2, user.getEmail());
            ps.setString(3, user.getUsername());
            ps.setString(4, user.getPassword());
            ps.setString(5, user.getPhoneNumber());
            ps.setString(6, user.getAddress());
            ps.setString(7, user.getRole().toString());
            return ps;
        }, keyHolder);

        System.out.println("Generated Keys: " + keyHolder.getKeys());

        Map<String, Object> keys = keyHolder.getKeys();
        Long generatedId = ((Number) Objects.requireNonNull(keys).get("id")).longValue();

        user.setId(generatedId);

        return user;
    }

    public int delete(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        return jdbcTemplate.update(sql, id);
    }

    public Optional<UserModel> findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";

        RowMapper<UserModel> rowMapper = (rs, rowNum) -> {
            UserModel user = new UserModel();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setPhoneNumber(rs.getString("phone_number"));
            user.setAddress(rs.getString("address"));
            user.setRole(Role.valueOf(rs.getString("role")));
            return user;
        };

        return jdbcTemplate.query(sql, rowMapper, username).stream().findFirst();
    }

    public Optional<UserModel> findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ?";

        RowMapper<UserModel> rowMapper = (rs, rowNum) -> {
            UserModel user = new UserModel();
            user.setId(rs.getLong("id"));
            user.setName(rs.getString("name"));
            user.setEmail(rs.getString("email"));
            user.setUsername(rs.getString("username"));
            user.setPassword(rs.getString("password"));
            user.setPhoneNumber(rs.getString("phone_number"));
            user.setAddress(rs.getString("address"));
            user.setRole(Role.valueOf(rs.getString("role")));
            return user;
        };

        return jdbcTemplate.query(sql, rowMapper, id).stream().findFirst();
    }
}
