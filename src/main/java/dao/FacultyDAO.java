package dao;

import model.Faculty;
import util.Authtication;
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
                String hashedpass=Authtication.hashedPass(pass);
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
        String hashed= Authtication.hashedPass(pass);
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
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
            e.printStackTrace();
            return false;
        }
    }
}
