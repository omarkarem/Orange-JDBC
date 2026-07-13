package com.orange;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.orange.config.DatabaseConfig;
import com.orange.dao.InternDao;
import com.orange.model.Intern;

public class Main {
    public static void main(String[] args) {
        // Exercise 1: Test connection
        try (Connection con = DatabaseConfig.getConnection()) {
            System.out.println("connected to postgresql");
            System.out.println("Database: " + con.getCatalog());
        } catch (SQLException e) {
            System.out.println("connection failed");
            System.out.println("error: " + e.getMessage());
        }

        // Exercise 2: Read All Interns
        InternDao internDao = new InternDao();
        List<Intern> all = internDao.findAllInterns();
        System.out.println("=== All Interns ===");
        for (Intern intern : all) {
            System.out.println(intern);
        }
    }
}
