package dashboard;

import dao.*;
import model.Student;

import java.util.Scanner;

public class adminDashboard {
    AdminDAO admin = new AdminDAO();
    StudentDAO student = new StudentDAO();
    FacultyDAO faculty = new FacultyDAO();
    CourseDAO course = new CourseDAO();
    EnrollmentDAO enroll = new EnrollmentDAO();

    Scanner obj = new Scanner(System.in);

    public void display() {
        System.out.print("Enter ID: ");
        String id = obj.next();
        System.out.print("Enter Password: ");
        String pass = obj.next();
        if (admin.login(id, pass)) {
            System.out.println("Login Successfull... Welcome! " + id);
            dashboard(id);
        } else {
            System.out.println("Invalid ID/Password");
        }
    }

    public void dashboard(String id) {
        boolean isValid = true;
        while (isValid) {
            System.out.println("====== Admin Panel ======");
            System.out.println("1. Add Student\n2. Add Faculty\n3. Add Course\n4. Enroll Student in Course" +
                    "\n5. Delete Student / Faculty / Course\n6. Update Student / Faculty / Course" +
                    "\n7. View All Students / Faculty / Courses / Enrollments\n8. Exit to Main Menu");
            System.out.println("Enter Choice: ");
            int choice = obj.nextInt();
            switch (choice) {
                case 1:
                    insertStudent();
                    break;
            }

        }
    }

    public void insertStudent() {
        System.out.println("Enter ID: ");
        String id = obj.next();
        System.out.println("Enter Name: ");
        String name = obj.next();
        System.out.println("Enter Mail Id: ");
        String mail = obj.next();
        System.out.println("Enter Major: ");
        String major = obj.next();
        System.out.println("Enter Year: ");
        int year = obj.nextInt();
        System.out.println("Enter Password: ");
        String pass = obj.next();
        if (student.isAlreadyInserted(id)) {
            System.out.println("Student already Exist");
        } else {
            student.insertStudent(new Student(id, name, mail, major, year, pass));
            System.out.println("Student added Sucessfully!");
        }
    }
}
