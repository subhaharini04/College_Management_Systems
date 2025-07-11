import dao.FacultyDAO;
import dao.StudentDAO;
import model.Faculty;
import model.Student;
import util.DBConnection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("\n Database connection successful!");
        } catch (Exception e) {
            System.err.println("Connection failed: " + e.getMessage());
        }

        //FACULTY
        FacultyDAO facultyDAO=new FacultyDAO();
       Faculty f= facultyDAO.getFacultyById("25F102");
       if(f!=null){
           System.out.println(f.getName());;
       }else{
           System.out.println("failed");
       }
    }
}
