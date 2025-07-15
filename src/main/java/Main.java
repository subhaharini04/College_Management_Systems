import dao.EnrollmentDAO;
import dao.FacultyDAO;
import dao.StudentDAO;
import dao.CourseDAO;
import model.Faculty;
import model.Student;
import model.Course;
import util.DBConnection;
import java.util.List;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("\n Database connection successful!");
        } catch (Exception e) {
            System.err.println("Connection failed: " + e.getMessage());
        }

        EnrollmentDAO enrollmentDAO=new EnrollmentDAO();
        enrollmentDAO.enrollStudents( "S202501","CSE101");
    }
}
