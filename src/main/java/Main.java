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

        StudentDAO studentDAO=new StudentDAO();
        Student fetchedStudent = studentDAO.getStudentById("S2024001");
        if (fetchedStudent != null) {
            System.out.println("Found: ");
            fetchedStudent.displayInfo();
        } else {
            System.out.println("Student not found!");
        }
        boolean success = studentDAO.updateEmail("S2024001", "hani11@college.edu");
        if (success){
            Student updateStudent = studentDAO.getStudentById("S2024001");
            System.out.println("Email update succe");
            updateStudent.displayInfo();
        }else{
            System.out.println("update failed");
        }
    }
}
