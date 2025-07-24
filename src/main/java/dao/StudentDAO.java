package dao;

import model.Student;
import util.Authentication;
import util.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    public boolean login(String id,String pass){
        String sql="SELECT password FROM students WHERE id=?";
        try(Connection conn=DBConnection.getConnection();
        PreparedStatement stmt= conn.prepareStatement(sql)) {
            stmt.setString(1,id);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                String originalPass= rs.getString("password");
                String hashed= Authentication.hashedPass(pass);
                return originalPass.equals(hashed);
            }else {
                return false;
            }
        }catch (SQLException e){
            System.out.println("Error.."+e.getMessage());
            return false;
        }
    }

    public boolean isFirstLogin(String studentId) {
        String sql = "SELECT first_login FROM students WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("first_login");
            }
        } catch (SQLException e) {
            System.out.println("Error checking first login: " + e.getMessage());
        }
        return false;
    }


    public void updatePassword(String studentId, String newPass) {
        String hashed = Authentication.hashedPass(newPass);
        String sql = "UPDATE students SET password = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, hashed);
            stmt.setString(2, studentId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating password: " + e.getMessage());
        }
    }

    public void markFirstLoginComplete(String studentId) {
        String sql = "UPDATE students SET first_login = FALSE WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating first_login status: " + e.getMessage());
        }
    }

    public boolean isAlreadyInserted(String studentId) {
        String sql = "SELECT * FROM students WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("Error checking inserting: " + e.getMessage());
            return false;
        }
    }

    public void insertStudent(Student student) {
        String pass = student.getPassword();
        String hashedPassword = Authentication.hashedPass(pass);
        String sql = "INSERT INTO students(id, name,email, major, year,password) VALUES(?, ?, ?, ?, ?,?);";
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
            System.out.println("Error in Database : "+e.getMessage());
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
            System.out.println("Error in Database : "+e.getMessage());
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
            System.out.println("Error in Database : "+e.getMessage());
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
