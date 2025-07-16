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
        System.out.println("\n📚 Students enrolled in CSE101:");
        List<Student> students = enrollmentDAO.getStudentsByCourse("CSE101");
        for (Student s : students) {
            s.displayInfo();  // uses your overridden displayInfo()
            System.out.println("-----------");
        }

        // 🔹 Test: Get courses a student is enrolled in
        System.out.println("\n🧑‍🎓 Courses for student S202501:");
        List<Course> courses = enrollmentDAO.getCoursesByStudent("S202501");
        for (Course c : courses) {
            System.out.println(c.getId() + " - " + c.getCourseName() + " - " + c.getFacultyId());
        }    }
}
