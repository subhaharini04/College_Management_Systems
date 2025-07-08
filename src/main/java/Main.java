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

        //STUDENT OPERATION;
        StudentDAO studentDAO = new StudentDAO();
        boolean isDeleted = studentDAO.deleteStudent("S2024001");
        if (isDeleted) {
            System.out.println("Student removed");
        } else {
            System.out.println("failed");
        }
    }
}
