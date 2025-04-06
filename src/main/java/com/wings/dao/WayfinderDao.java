package com.wings.dao;

import com.wings.model.Wayfinder;
import com.wings.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WayfinderDao {

    public List<Wayfinder> findAll() {
        List<Wayfinder> list = new ArrayList<>();
        String sql = "SELECT * FROM wayfinders";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(Wayfinder.builder()
                        .wayfinderId(rs.getInt("wayfinder_id"))
                        .fullName(rs.getString("full_name"))
                        .build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
