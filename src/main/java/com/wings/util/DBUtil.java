package com.wings.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {

    static {
        try {
            Class.forName(ConfigUtil.get("db.driver"));
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Failed to load DB driver", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(
                ConfigUtil.get("db.url"),
                ConfigUtil.get("db.username"),
                ConfigUtil.get("db.password")
        );
    }
}