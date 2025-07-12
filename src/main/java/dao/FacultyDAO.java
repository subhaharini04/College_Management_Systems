package dao;
import model.Faculty;
import util.DBConnection;
import java.sql.*;

public class FacultyDAO {
    public void insertFaculty(Faculty faculty){
        String sql="INSERT INTO faculty(id,name,email,department) VALUES(?,?,?,?)";
        try(Connection conn= DBConnection.getConnection();
        PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setString(1, faculty.getId());
            stmt.setString(2, faculty.getName());
            stmt.setString(3, faculty.getEmail());
            stmt.setString(4, faculty.getDept());
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error in Insert Faculty : "+e.getMessage());
            e.printStackTrace();
        }
    }

    public Faculty getFacultyById(String id){
        String sql="SELECT * FROM faculty WHERE id= ?";
        try(Connection conn=DBConnection.getConnection();
        PreparedStatement stmt=conn.prepareStatement(sql)){
            stmt.setString(1,id);
            try(ResultSet rs=stmt.executeQuery()){
                if(rs.next()){
                    return new Faculty(
                       rs.getString("id"),
                       rs.getString("name"),
                       rs.getString("email"),
                       rs.getString("department")
                    );
                }
            }
        }catch(SQLException e){
            System.out.println("Error in fetching Facluty : "+e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateEmail(String id){
        return false;
    }
}
