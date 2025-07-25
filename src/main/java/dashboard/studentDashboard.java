package dashboard;

import dao.CourseDAO;
import dao.EnrollmentDAO;
import dao.StudentDAO;
import model.Course;

import java.util.List;
import java.util.Scanner;

public class studentDashboard {
    EnrollmentDAO enroll = new EnrollmentDAO();
    StudentDAO student = new StudentDAO();
    CourseDAO course = new CourseDAO();
    Scanner obj = new Scanner(System.in);

    public void display() {
        System.out.println("------------------------");
        System.out.println("----Student Dashboard----");
        System.out.println("------------------------");
        System.out.print("Enter ID: ");
        String id = obj.next();
        System.out.print("Enter Password: ");
        String pass = obj.next();
        if (student.login(id, pass)) {
            if (student.isFirstLogin(id)) {
                System.out.println("First-time login. Please reset your password:");
                System.out.print("Enter new password: ");
                String newPass = obj.next();
                student.updatePassword(id, newPass);
                student.markFirstLoginComplete(id);
                System.out.println("Password updated successfully.");
            }
            System.out.println("Login Successfull... Welcome! " + id);
            dashboard(id);
        } else {
            System.out.println("Invalid ID/Password");
        }
    }

    public void dashboard(String id) {
        boolean isValid = true;
        while (isValid) {
            System.out.println("====== Student Portal ======");
            System.out.println("1. View My Courses\n2. Enroll in a Course\n3. Exit to Main Menu");
            System.out.print("Enter choice: ");
            int choice = obj.nextInt();
            switch (choice) {
                case 1:
                    List<Course> courseList = enroll.getCoursesByStudent(id);
                    System.out.println("Available Courses:");
                    if (courseList.isEmpty()) {
                        System.out.println("Not enrolled in any course");
                    }
                    for (Course c : courseList) {
                        System.out.println("ID : " + c.getId() + " Name : " + c.getCourseName());
                    }
                    System.out.println("-----------------------------");
                    break;
                case 2:
                    List<Course> courseList1 = course.getAllCourses();
                    System.out.println("Available Courses:");
                    for (Course c : courseList1) {
                        System.out.println("ID : " + c.getId() + " Name : " + c.getCourseName());
                    }
                    System.out.println("-----------------------------");
                    System.out.print("Enter Course ID to Enroll: ");
                    String courseId = obj.next();
                    if (enroll.isAlreadyEnrolled(id, courseId)) {
                        System.out.println("Already Enrolled");
                    } else {
                        enroll.enrollStudents(id, courseId);
                        System.out.println("Enrolled Successfully");
                    }
                    break;
                case 3:
                    isValid = false;
                    System.out.println("Exiting.. Thank You!..\n");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}
