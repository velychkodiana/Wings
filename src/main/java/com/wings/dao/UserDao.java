package com.wings.dao;

import com.wings.model.User;
import com.wings.util.DBUtil;

import java.sql.*;
import java.util.Optional;

public class UserDao {

    public Optional<User> login(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, username);
            ps.setString(2, password);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                User user = User.builder()
                        .userId(rs.getInt("user_id"))
                        .fullName(rs.getString("full_name"))
                        .position(rs.getString("position"))
                        .username(rs.getString("username"))
                        .password(rs.getString("password"))
                        .build();
                return Optional.of(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return Optional.empty();
    }
}
