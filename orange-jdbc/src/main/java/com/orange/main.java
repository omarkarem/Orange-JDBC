package com.orange;

import java.sql.Connection;
import java.sql.SQLException;
import com.orange.config.DatabaseConfig;

public class Main {
    public static void main(String[] args) {
        try (Connection con = DatabaseConfig.getConnection()) {
            System.out.println("connected to postgresql");
            System.out.println("Database: " + con.getCatalog());
        } catch (SQLException e) {
            System.out.println("connection failed");
            System.out.println("error: " + e.getMessage());
        }
    }
}
