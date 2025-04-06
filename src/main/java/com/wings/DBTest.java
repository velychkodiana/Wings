package com.wings;

import com.wings.util.DBUtil;

import java.sql.Connection;

public class DBTest {
    public static void main(String[] args) {
        try (Connection conn = DBUtil.getConnection()) {
            if (conn != null && !conn.isClosed()) {
                System.out.println("✅ Database connection is successful!");
            } else {
                System.out.println("❌ Failed to connect to the database.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}