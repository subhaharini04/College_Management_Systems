package dashboard;

import dao.CourseDAO;
import dao.EnrollmentDAO;
import dao.FacultyDAO;
import model.Course;
import model.Enrollment;
import model.Student;

import java.util.List;
import java.util.Scanner;

public class facultyDashboard {
    CourseDAO course = new CourseDAO();
    EnrollmentDAO enroll = new EnrollmentDAO();
    FacultyDAO faculty = new FacultyDAO();
    Scanner obj = new Scanner(System.in);

    public void display() {
        System.out.println("------------------------");
        System.out.println("----Faculty Dashboard----");
        System.out.println("------------------------");
        System.out.print("Enter Id: ");
        String id = obj.next();
        System.out.print("Enter Password: ");
        String pass = obj.next();
        if (faculty.login(id, pass)) {
            if (faculty.isFirstLogin(id)) {
                System.out.println("First-time login. Please reset your password:");
                System.out.print("Enter new password: ");
                String newPass = obj.next();
                faculty.updatePassword(id, newPass);
                faculty.markFirstLoginComplete(id);
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
                        System.out.println("ID: " + c.getId() + "   Course: " + c.getCourseName());
                    }
                    System.out.println("-----------------------------");
                    break;
                case 2:
                    System.out.println("Courses :");
                    for (Course c : courseList) {
                        System.out.println("ID: " + c.getId() + "  Course: " + c.getCourseName());
                    }
                    System.out.println("-----------------------------");
                    System.out.print("Enter CourseId: ");
                    String courseid = obj.next();
                    boolean found = false;
                    for (Course c : courseList) {
                        if (c.getId().equals(courseid)) {
                            found = true;
                            break;
                        }
                    }
                    if (found) {
                       List<Student> enrollList = enroll.getStudentsByCourse(courseid);
                       for(Student s:enrollList){
                           System.out.println("ID :  "+s.getId()+"  Name : "+s.getName()+"  Year : "+s.getYear()+" Major : "+s.getMajor());
                       }
                        System.out.println("-----------------------------");
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
