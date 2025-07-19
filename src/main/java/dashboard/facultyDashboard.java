package dashboard;

import dao.CourseDAO;
import dao.EnrollmentDAO;

import java.util.Scanner;

public class facultyDashboard {
    CourseDAO course = new CourseDAO();

    public void display() {
        Scanner obj = new Scanner(System.in);
        System.out.println("====== Faculty Portal ======");
        System.out.println("1. View My Courses\n2. View Students in My Course\n3. Exit to Main Menu\nEnter choice: ");
        int choice = obj.nextInt();
        boolean isValid = true;
        while (isValid) {
            switch (choice) {
                case 1:
                    course.courseByFaculty();
                    break;
                case 2:
                    course.;
                    break;
                case 3:
                    isValid = false;
            }
        }
    }
}
