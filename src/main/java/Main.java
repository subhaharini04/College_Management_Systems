import dao.EnrollmentDAO;
import dao.FacultyDAO;
import dao.StudentDAO;
import dao.CourseDAO;
import dashboard.adminDashboard;
import dashboard.facultyDashboard;
import dashboard.studentDashboard;
import model.Admin;
import model.Faculty;
import model.Student;
import model.Course;
import util.DBConnection;

import java.util.List;
import java.sql.Connection;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("Database connection successful!");
        } catch (Exception e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
        //====== Welcome to College Management System ======
        //Select your role:
        //1. Student
        //2. Faculty
        //3. Admin
        //4. Exit
        //Enter choice:
        //====== Admin Panel ======
        //1. Add Student
        //2. Add Faculty
        //3. Add Course
        //4. Enroll Student in Course
        //5. Delete Student / Faculty / Course
        //6. Update Email / Department / Year
        //7. View All Students / Faculty / Courses / Enrollments
        //8. Exit to Main Menu
        //====== Faculty Portal ======
        //1. View My Courses
        //2. View Students in My Course
        //3. Exit to Main Menu
        //====== Student Portal ======
        //1. View My Courses
        //2. Enroll in a Course
        //3. Exit to Main Menu
        studentDashboard student = new studentDashboard();
        facultyDashboard faculty = new facultyDashboard();
        adminDashboard admin=new adminDashboard();
        System.out.println("====== Welcome to College Management System ======");
        System.out.println("Select your role:");
        System.out.println("1. Student\n2. Faculty\n3. Admin\n4.Exit\nEnter choice:");
        int choice = obj.nextInt();
        boolean isValid = true;
        while (isValid) {
            switch (choice) {
                case 1:
                    student.display();
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    isValid=false;
            }
        }
    }
}
