package com.wings.dao;

import com.wings.model.Attendant;
import com.wings.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AttendantDao {

    public List<Attendant> findAll() {
        List<Attendant> list = new ArrayList<>();
        String sql = "SELECT * FROM attendants";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(Attendant.builder()
                        .attendantId(rs.getInt("attendant_id"))
                        .fullName(rs.getString("full_name"))
                        .build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}
