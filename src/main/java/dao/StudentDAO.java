package dao;

import model.Student;
import util.DBConnection;

import java.sql.*;

public class StudentDAO {
    public void insertStudent(Student student) {
        String sql = "INSERT INTO students(id, name,email, major, year) VALUES(?, ?, ?, ?, ?);";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getId());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getEmail());
            stmt.setString(4, student.getMajor());
            stmt.setInt(5, student.getYear());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting student: " + e.getMessage());
            e.printStackTrace();
        }
    }
    public void updateStudent(Student student){

    }
    public void deleteStudent(Student student){

    }
}
