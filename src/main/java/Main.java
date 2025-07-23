import dao.EnrollmentDAO;
import dao.FacultyDAO;
import dao.StudentDAO;
import dao.CourseDAO;
import dashboard.adminDashboard;
import dashboard.facultyDashboard;
import dashboard.studentDashboard;
import util.Authtication;
import util.DBConnection;

import java.sql.Connection;
import java.util.Scanner;

public class Main {
    private static Authtication Authentication;

    public static void main(String[] args) {
        Scanner obj = new Scanner(System.in);
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("Database connection successful!");
        } catch (Exception e) {
            System.err.println("Connection failed: " + e.getMessage());
        }
        studentDashboard student = new studentDashboard();
        facultyDashboard faculty = new facultyDashboard();
        adminDashboard admin = new adminDashboard();
        System.out.println();
        boolean isValid = true;
        while (isValid) {
            System.out.println("====== Welcome to College Management System ======");
            System.out.println("Select your role:");
            System.out.println("1. Student\n2. Faculty\n3. Admin\n4. Exit");
            System.out.print("Enter choice: ");
            int choice = obj.nextInt();
            switch (choice) {
                case 1:
                    student.display();
                    break;
                case 2:
                    faculty.display();
                    break;
                case 3:
                    admin.display();
                    break;
                case 4:
                    isValid = false;
                    System.out.println("Exiting...Thank You!...");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}
