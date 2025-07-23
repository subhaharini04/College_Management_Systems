package dashboard;

import dao.CourseDAO;
import dao.EnrollmentDAO;
import dao.StudentDAO;
import model.Course;

import java.util.List;
import java.util.Scanner;

public class studentDashboard {
    EnrollmentDAO enroll = new EnrollmentDAO();
    StudentDAO student=new StudentDAO();
    CourseDAO course=new CourseDAO();
    Scanner obj = new Scanner(System.in);

    public void display(){
        System.out.print("Enter ID: ");
        String id=obj.next();
        System.out.print("Enter Password: ");
        String pass=obj.next();
        if(student.login(id,pass)){
            System.out.println("Login Successfull... Welcome! "+id);
            dashboard(id);
        }else {
            System.out.println("Invalid ID/Password");
        }
    }
    public void dashboard(String id) {
        boolean isValid = true;
        while (isValid) {
        System.out.println("====== Student Portal ======");
        System.out.println("1. View My Courses\n2. Enroll in a Course\n3. Exit to Main Menu\nEnter choice: ");
        int choice = obj.nextInt();
            switch (choice) {
                case 1:
                    enroll.getCoursesByStudent(id);
                    break;
                case 2:
                    List<Course> courseList=course.getAllCourses();
                    System.out.println("Available Courses:");
                    for(Course c:courseList){
                        System.out.println("ID : "+c.getId()+"Name : "+c.getCourseName());
                    }
                    System.out.println("Enter Course ID to Enroll: ");
                    String courseId=obj.next();
                    if(enroll.isAlreadyEnrolled(id,courseId)){
                        System.out.println("Already Enrolled");
                    }else {
                        enroll.enrollStudents(id,courseId);
                    }
                    break;
                case 3:
                    isValid = false;
                    System.out.println("Exiting.. Thank You!..");
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }
}
