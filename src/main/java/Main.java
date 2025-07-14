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

        //FACULTY

        CourseDAO dao = new CourseDAO();
        List<Course> courses = dao.courseByFaculty("F202501");

        for (Course c : courses) {
            System.out.println(c.getId() + " - " + c.getCourseName());
        }
    }
}
