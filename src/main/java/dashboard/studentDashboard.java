package dashboard;

import dao.EnrollmentDAO;

import java.util.Scanner;

public class studentDashboard {
    EnrollmentDAO enroll = new EnrollmentDAO();

    public void display() {
        Scanner obj = new Scanner(System.in);
        System.out.println("====== Student Portal ======");
        System.out.println("1. View My Courses\n2. Enroll in a Course\n3. Exit to Main Menu\nEnter choice: ");
        int choice = obj.nextInt();
        boolean isValid = true;
        while (isValid) {
            switch (choice) {
                case 1:
                    enroll.getCoursesByStudent();
                    break;
                case 2:
                    enroll.enrollStudents();
                    break;
                case 3:
                    isValid = false;
            }
        }
    }
}
