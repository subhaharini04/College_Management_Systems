package dao;

import model.Faculty;
import model.Student;
import util.Authtication;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {
    public void insertStudent(Student student) {
        String pass = student.getPassword();
        String hashedPassword = Authtication.hashedPass(pass);
        String sql = "INSERT INTO students(id, name,email, major, year,password) VALUES(?, ?, ?, ?, ?);";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student.getId());
            stmt.setString(2, student.getName());
            stmt.setString(3, student.getEmail());
            stmt.setString(4, student.getMajor());
            stmt.setInt(5, student.getYear());
            stmt.setString(6, hashedPassword);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting student: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public Student getStudentById(String id) {
        String sql = "SELECT * FROM students WHERE id= ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Student(
                            rs.getString("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("major"),
                            rs.getInt("year"),
                            rs.getString("password")
                    );
                }
            }
        } catch (SQLException e) {
            System.err.println("Error fetching Student - " + e.getMessage());
        }
        return null;
    }

    public boolean updateEmail(String id, String newEmail) {
        String sql = "UPDATE students SET email=? WHERE id=?";
        if (newEmail == null || !newEmail.contains("@")) {
            System.err.println("Invalid email format");
            return false;
        }
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newEmail);
            stmt.setString(2, id);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateYear(String id, int newYear) {
        String sql = "UPDATE students SET year=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, newYear);
            stmt.setString(2, id);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateMajor(String id, String newMajor) {
        String sql = "UPDATE students SET major=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newMajor);
            stmt.setString(2, id);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteStudent(String id) {
        String sql = "DELETE FROM students WHERE id= ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            int rowDeleted = stmt.executeUpdate();
            return rowDeleted > 0;
        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    public static List<Student> getAllStudent() {
        List<Student> studentList = new ArrayList<>();
        String sql = "SELECT * FROM students";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Student student = new Student(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("major"),
                        rs.getInt("year"),
                        rs.getString("password")
                );
                studentList.add(student);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching Stuent list: " + e.getMessage());
        }
        return studentList;
    }

}
