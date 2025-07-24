package dao;

import model.Faculty;
import util.Authentication;
import util.DBConnection;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;


public class FacultyDAO {

    public boolean login(String id,String pass){
        String sql="SELECT password FROM facultys WHERE id=?";
        try(Connection conn=DBConnection.getConnection();
        PreparedStatement stmt=conn.prepareStatement(sql)) {
            stmt.setString(1,id);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                String originalPass= rs.getString("password");
                String hashedpass= Authentication.hashedPass(pass);
                return originalPass.equals(hashedpass);
            }else {
                return false;
            }
        }
        catch (SQLException e){
            System.out.println("Error in login "+e.getMessage());
            return false;
        }
    }

    public boolean isFirstLogin(String facultyId) {
        String sql = "SELECT first_login FROM facultys WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, facultyId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("first_login");
            }
        } catch (SQLException e) {
            System.out.println("Error checking first login: " + e.getMessage());
        }
        return false;
    }


    public void updatePassword(String facultyId, String newPass) {
        String hashed = Authentication.hashedPass(newPass);
        String sql = "UPDATE facultys SET password = ? WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, hashed);
            stmt.setString(2, facultyId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating password: " + e.getMessage());
        }
    }

    public void markFirstLoginComplete(String facultyId) {
        String sql = "UPDATE facultys SET first_login = FALSE WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, facultyId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error updating first_login status: " + e.getMessage());
        }
    }

    public boolean isAlreadyInserted(String facultyId) {
        String sql = "SELECT * FROM facultys WHERE id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, facultyId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.err.println("Error checking inserting: " + e.getMessage());
            return false;
        }
    }

    public void insertFaculty(Faculty faculty) {
        String pass= faculty.getPassword();
        String hashed= Authentication.hashedPass(pass);
        String sql = "INSERT INTO facultys(id,name,email,department,password) VALUES(?,?,?,?,?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, faculty.getId());
            stmt.setString(2, faculty.getName());
            stmt.setString(3, faculty.getEmail());
            stmt.setString(4, faculty.getDept());
            stmt.setString(5,hashed);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in Insert Faculty : " + e.getMessage());
        }
    }

    public Faculty getFacultyById(String id) {
        String sql = "SELECT * FROM facultys WHERE id= ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Faculty(
                            rs.getString("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("department"),
                            rs.getString("password")
                    );
                }
            }
        } catch (SQLException e) {
            System.out.println("Error in fetching Facluty : " + e.getMessage());
        }
        return null;
    }

    public List<Faculty> getAllFaculty() {
        List<Faculty> facultyList = new ArrayList<>();
        String sql = "SELECT * FROM facultys";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Faculty faculty = new Faculty(
                        rs.getString("id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("department"),
                        rs.getString("password")
                );
                facultyList.add(faculty);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching faculty list: " + e.getMessage());
        }
        return facultyList;
    }

    public boolean updateEmail(String id, String newEmail) {
        String sql = "UPDATE facultys SET email=? WHERE id=?";
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
    public boolean updateDept(String id, String newDept) {
        String sql = "UPDATE facultys SET department=? WHERE id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newDept);
            stmt.setString(2, id);
            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            System.out.println("Error in Database : "+e.getMessage());
            return false;
        }
    }

    public boolean deleteFaculty(String id) {
        String sql = "DELETE FROM facultys WHERE id= ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            int rowDeleted = stmt.executeUpdate();
            return rowDeleted>0;
        } catch (SQLException e) {
            System.err.println("Error deleting student: " + e.getMessage());
            return false;
        }
    }
}
