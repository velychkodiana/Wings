package com.wings.dao;

import com.wings.model.Pilot;
import com.wings.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PilotDao {

    public List<Pilot> findAll() {
        List<Pilot> list = new ArrayList<>();
        String sql = "SELECT * FROM pilots";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(Pilot.builder()
                        .pilotId(rs.getInt("pilot_id"))
                        .fullName(rs.getString("full_name"))
                        .build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}