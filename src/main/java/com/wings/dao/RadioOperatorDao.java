package com.wings.dao;

import com.wings.model.RadioOperator;
import com.wings.util.DBUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RadioOperatorDao {

    public List<RadioOperator> findAll() {
        List<RadioOperator> list = new ArrayList<>();
        String sql = "SELECT * FROM radio_operators";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(RadioOperator.builder()
                        .radioOperatorId(rs.getInt("radio_operator_id"))
                        .fullName(rs.getString("full_name"))
                        .build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }
}