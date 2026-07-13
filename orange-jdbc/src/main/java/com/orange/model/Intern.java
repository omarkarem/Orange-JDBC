package com.orange.model;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import com.orange.config.DatabaseConfig;

public class Intern {
    private int id;
    private String fullname;
    private String email;
    private LocalDate joinDate;
    private int trackId;
    private Integer mentorId;

    public Intern(int id, String fullname, String email, LocalDate joinDate, int trackId, Integer mentorId) {
        this.id = id;
        this.fullname = fullname;
        this.email = email;
        this.joinDate = joinDate;
        this.trackId = trackId;
        this.mentorId = mentorId;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getFullname() {
        return fullname;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getJoinDate() {
        return joinDate;
    }

    public int getTrackId() {
        return trackId;
    }

    public Integer getMentorId() {
        return mentorId;
    }

    // Setters
    public void setId(int id) {
        this.id = id;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setJoinDate(LocalDate joinDate) {
        this.joinDate = joinDate;
    }

    public void setTrackId(int trackId) {
        this.trackId = trackId;
    }

    public void setMentorId(Integer mentorId) {
        this.mentorId = mentorId;
    }

    @Override
    public String toString() {
        return "Intern { id=" + id + ", name='" + fullName + "', email='" + email + "joinDate=" + joinDate
                + ", trackId=" + trackId + ", mentorId=" + mentorId + "}";
    }

    public List<Intern> findAllInterns() {
        List<Intern> interns = new ArrayList<>();
        String sql = "SELECT * from interns";
        try (Connection conn = DatabaseConfig.getConnection();
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Intern intern = new Intern(
                        rs.getInt("id"),
                        rs.getString("fullname"),
                        rs.getString("email"),
                        rs.getDate("joinDate").toLocalDate(),
                        rs.getInt("trackId"),
                        rs.getInt("mentorId"));
                interns.add(intern);
            }
        } catch (SQLException e) {
            System.out.println("error reading interns: " + e.getMessage());
        }
        return interns;
    }
}
