package com.orange.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.orange.config.DatabaseConfig;
import com.orange.model.Intern;

public class InternDao {

    public List<Intern> findAllInterns() {
        List<Intern> interns = new ArrayList<>();
        String sql = "SELECT * FROM interns";
        
        try (Connection conn = DatabaseConfig.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
             
            while (rs.next()) {
                // We use the exact column names as they appear in the PostgreSQL database
                Intern intern = new Intern(
                        rs.getInt("id"),
                        rs.getString("full_name"),
                        rs.getString("email"),
                        rs.getDate("join_date").toLocalDate(),
                        rs.getInt("track_id"),
                        rs.getObject("mentor_id") != null ? rs.getInt("mentor_id") : null
                );
                interns.add(intern);
            }
        } catch (SQLException e) {
            System.out.println("Error reading interns: " + e.getMessage());
        }
        
        return interns;
    }
}
