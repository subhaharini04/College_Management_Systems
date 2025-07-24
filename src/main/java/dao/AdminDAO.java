package dao;

import util.Authentication;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminDAO {
    public boolean login(String id,String pass){
        String sql="SELECT password FROM admin WHERE ID=?";
        try(Connection conn= DBConnection.getConnection();
            PreparedStatement stmt= conn.prepareStatement(sql)){
            stmt.setString(1,id);
            ResultSet rs=stmt.executeQuery();
            if(rs.next()){
                String originalPass=rs.getString("password");
                String hashed= Authentication.hashedPass(pass);
                return originalPass.equals(hashed);
            }
            else {
                return false;
            }
        }catch (SQLException e){
            System.out.println("Error in login:"+e.getMessage());
            return false;
        }
    }
}
