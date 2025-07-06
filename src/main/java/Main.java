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

        //STUDENT OPERATION
        Student newStudent=new Student("S2024001", "Rahul", "rahul@college.edu", "Computer Science", 2);
        new StudentDAO().insertStudent(newStudent);
        newStudent.displayInfo();
    }
}
