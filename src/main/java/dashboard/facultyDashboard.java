package dashboard;

import dao.CourseDAO;
import dao.EnrollmentDAO;
import dao.FacultyDAO;
import model.Course;

import java.util.List;
import java.util.Scanner;

public class facultyDashboard {
    CourseDAO course = new CourseDAO();
    EnrollmentDAO enroll = new EnrollmentDAO();
    FacultyDAO faculty = new FacultyDAO();
    Scanner obj = new Scanner(System.in);

    public void display() {
        System.out.println("Enter Id: ");
        String id = obj.next();
        System.out.println("Enter Password");
        String pass = obj.next();
        if (faculty.login(id, pass)) {
            System.out.println("Login Successfull... Welcom! " + id);
            dashboard(id);
        } else {
            System.out.println("Invalid ID/Password");
        }
    }

    public void dashboard(String id) {
        boolean isValid = true;
        List<Course> courseList = course.courseByFaculty(id);
        while (isValid) {
            System.out.println("====== Faculty Portal ======");
            System.out.println("1. View My Courses\n2. View Students in My Course\n3. Exit to Main Menu");
            System.out.print("Enter choice: ");
            int choice = obj.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Courses :");
                    for (Course c : courseList) {
                        System.out.println("ID: " + c.getId() + "Course: " + c.getCourseName());
                    }
                    break;
                case 2:
                    System.out.println("Courses :");
                    for (Course c : courseList) {
                        System.out.println("ID: " + c.getId() + "Course: " + c.getCourseName());
                    }
                    System.out.println("Enter CourseId: ");
                    String courseid = obj.next();
                    boolean found = false;
                    for (Course c : courseList) {
                        if (c.getId().equals(courseid)) {
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                        enroll.getStudentsByCourse(courseid);
                    } else {
                        System.out.println("Invalid Course Id");
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
