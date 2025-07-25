package dashboard;

import dao.*;
import model.Course;
import model.Enrollment;
import model.Faculty;
import model.Student;

import java.util.List;
import java.util.Scanner;

public class adminDashboard {
    AdminDAO admin = new AdminDAO();
    StudentDAO student = new StudentDAO();
    FacultyDAO faculty = new FacultyDAO();
    CourseDAO course = new CourseDAO();
    EnrollmentDAO enroll = new EnrollmentDAO();

    Scanner obj = new Scanner(System.in);

    public void display() {
        System.out.println("------------------------");
        System.out.println("----Admin Dashboard----");
        System.out.println("------------------------");
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
            System.out.print("Enter Choice: ");
            int choice = obj.nextInt();
            switch (choice) {
                case 1:
                    insertStudent();
                    break;
                case 2:
                    insertFaculty();
                    break;
                case 3:
                    insertCourse();
                    break;
                case 4:
                    List<Course> courseList1 = course.getAllCourses();
                    System.out.println("Available Courses:");
                    for (Course c : courseList1) {
                        System.out.println("ID : " + c.getId() + " Name : " + c.getCourseName());
                    }
                    System.out.println("-----------------------------");
                    System.out.print("Enter Student ID to Enroll: ");
                    String studentId = obj.next();
                    System.out.print("Enter Course ID to Enroll: ");
                    String courseId = obj.next();
                    if (enroll.isAlreadyEnrolled(studentId, courseId)) {
                        System.out.println("Already Enrolled");
                    } else {
                        enroll.enrollStudents(studentId, courseId);
                        System.out.println("Enrolled Successfully");
                    }
                    break;
                case 5:
                    delete();
                    break;
                case 6:
                    update();
                    break;
                case 7:
                    viewAll();
                    break;
                case 8:
                    System.out.println("Exiting..");
                    isValid = false;
                    break;
                default:
                    System.out.println("Invalid Choice");
            }

        }
    }

    public void insertStudent() {
        System.out.println("Enter the Student Detail");
        obj.nextLine();
        System.out.print("Enter ID: ");
        String id = obj.nextLine();
        System.out.print("Enter Name: ");
        String name = obj.nextLine();
        System.out.print("Enter Mail Id: ");
        String mail = obj.nextLine();
        System.out.print("Enter Major: ");
        String major = obj.nextLine();
        System.out.print("Enter Year: ");
        int year = obj.nextInt();
        obj.nextLine();
        System.out.print("Enter Password: ");
        String pass = obj.nextLine();
        if (student.isAlreadyInserted(id)) {
            System.out.println("Student already Exist");
        } else {
            student.insertStudent(new Student(id, name, mail, major, year, pass));
            System.out.println("Student added Sucessfully!\n");
        }
    }

    public void insertFaculty() {
        System.out.println("Enter the Faculty Detail");
        obj.nextLine();
        System.out.print("Enter ID: ");
        String id = obj.nextLine();
        System.out.print("Enter Name: ");
        String name = obj.nextLine();
        System.out.print("Enter Mail Id: ");
        String mail = obj.nextLine();
        System.out.print("Enter Dept: ");
        String major = obj.nextLine();
        System.out.print("Enter Password: ");
        String pass = obj.nextLine();
        if (faculty.isAlreadyInserted(id)) {
            System.out.println("Faculty already Exist");
        } else {
            faculty.insertFaculty(new Faculty(id, name, mail, major, pass));
            System.out.println("Faculty added Sucessfully!\n");
        }
    }

    public void insertCourse() {
        System.out.println("Enter the Course Detail");
        obj.nextLine();
        System.out.print("Enter ID: ");
        String id = obj.nextLine();
        System.out.print("Enter Course Name: ");
        String name = obj.nextLine();
        System.out.print("Enter Faculty Id: ");
        String facultyId = obj.nextLine();
        if (course.isAlreadyInserted(id)) {
            System.out.println("Course already Exist");
        } else {
            course.insertCourse(new Course(id, name, facultyId));
            System.out.println("Course added Sucessfully!\n");
        }
    }

    public void delete() {
        boolean isValid = true;
        while (isValid) {
            System.out.println("1. Delete Student\n2. Delete Faculty\n3. Delete Course\n4. Delete Enrollement\n5. Exit to Menu");
            System.out.print("Enter Your Choice: ");
            int choice = obj.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter the Student ID to Delete: ");
                    String studentId = obj.next();
                    if (student.deleteStudent(studentId)) {
                        System.out.println("Student Deleted Successfully\n");
                    } else {
                        System.out.println("Error in deleting\n");
                    }
                    break;
                case 2:
                    System.out.print("Enter the Faculty ID to Delete: ");
                    String facultyId = obj.next();
                    if (faculty.deleteFaculty(facultyId)) {
                        System.out.println("Faculty Deleted Successfully\n");
                    } else {
                        System.out.println("Error in deleting\n");
                    }
                    break;
                case 3:
                    System.out.print("Enter the Course ID to Delete: ");
                    String courseId = obj.next();
                    if (course.deleteCourse(courseId)) {
                        System.out.println("Course Deleted Successfully\n");
                    } else {
                        System.out.println("Error in deleting\n");
                    }
                    break;
                case 4:
                    System.out.print("Enter the Student ID: ");
                    String studentId1 = obj.next();
                    System.out.print("Enter the Course ID: ");
                    String courseId1 = obj.next();
                    if (enroll.deleteEnrollment(studentId1, courseId1)) {
                        System.out.println("Enrollement Deleted Successfully\n");
                    } else {
                        System.out.println("Error in deleting\n");
                    }
                    break;
                case 5:
                    System.out.println("Exiting..");
                    isValid = false;
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    public void update() {
        boolean isValid = true;
        while (isValid) {
            System.out.println("1. Update Student\n2. Update Faculty\n3. Update the Course Faculty\n4. Exit to Menu");
            System.out.print("Enter Your Choice: ");
            int choice = obj.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter the Student Id: ");
                    String studentId = obj.next();
                    updateStudent(studentId);
                    break;
                case 2:
                    System.out.print("Enter the Faculty Id: ");
                    String facultyId = obj.next();
                    updateFaculty(facultyId);
                    break;
                case 3:
                    System.out.print("Enter the Course Id: ");
                    String courseId = obj.next();
                    System.out.println("Enter the Faculty Id to Update Faculty: ");
                    String facultyId1 = obj.next();
                    if (course.updateFaculty(courseId, facultyId1)) {
                        System.out.println("Updated Succesfully");
                    } else {
                        System.out.println("Error in Updating");
                    }
                    break;
                case 4:
                    System.out.println("Exiting..");
                    isValid = false;
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    public void updateStudent(String id) {
        boolean isValid = true;
        while (isValid) {
            System.out.println("1. Update Email\n2. Update Major\n3. Update Year\n4. Exit to Menu");
            System.out.print("Enter Your Choice: ");
            int choice = obj.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter the New Email: ");
                    String mail = obj.next();
                    if (student.updateEmail(id, mail)) {
                        System.out.println("Updated Successfully");
                    }
                    break;
                case 2:
                    System.out.print("Enter the New Major: ");
                    String major = obj.next();
                    if (student.updateMajor(id, major)) {
                        System.out.println("Updated Successfully");
                    }
                    break;
                case 3:
                    System.out.print("Enter the New Year: ");
                    int year = obj.nextInt();
                    if (student.updateYear(id, year)) {
                        System.out.println("Updated Successfully");
                    }
                    break;
                case 4:
                    System.out.println("Exiting..");
                    isValid = false;
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    public void updateFaculty(String id) {
        boolean isValid = true;
        while (isValid) {
            System.out.println("1. Update Email\n2. Update Department\n3. Exit to Menu");
            System.out.print("Enter Your Choice: ");
            int choice = obj.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter the New Email: ");
                    String mail = obj.next();
                    if (faculty.updateEmail(id, mail)) {
                        System.out.println("Updated Successfully");
                    }
                    break;
                case 2:
                    System.out.print("Enter the New Department: ");
                    String dept = obj.next();
                    if (faculty.updateDept(id, dept)) {
                        System.out.println("Updated Successfully");
                    }
                    break;
                case 3:
                    System.out.println("Exiting..");
                    isValid = false;
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    public void viewAll() {
        boolean isValid = true;
        while (isValid) {
            System.out.println("1. View Student\n2. View Faculty\n3. View Course\n" +
                    "4. View Enrollement\n5. Exit to Menu");
            System.out.print("Enter Your Choice: ");
            int choice = obj.nextInt();
            switch (choice) {
                case 1:
                    viewStudents();
                    break;
                case 2:
                    viewFaculty();
                    break;
                case 3:
                    viewCourse();
                    break;
                case 4:
                    viewEnroll();
                    break;
                case 5:
                    System.out.println("Exiting..");
                    isValid = false;
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    public void viewStudents() {
        boolean isValid = true;
        while (isValid) {
            System.out.println("1. View Specific Student\n2. View All Student\n3. Exit to Menu");
            System.out.print("Enter Your Choice: ");
            int choice = obj.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Student ID: ");
                    String id = obj.next();
                    student.getStudentById(id).displayInfo();
                    break;
                case 2:
                    List<Student> studentList = student.getAllStudent();
                    for (Student s : studentList) {
                        System.out.println("Student:   " + s.getName() + "\n" +
                                "ID:        " + s.getId() + "\n" +
                                "Email:     " + s.getEmail() + "\n" +
                                "Major:     " + s.getMajor() + "\n" +
                                "Year:      " + s.getYear());
                        System.out.println("-----------------------------");
                    }
                    break;
                case 3:
                    System.out.println("Exiting..");
                    isValid = false;
                    break;
                default:
                    System.out.println("Invalid Choie");
            }
        }
    }

    public void viewFaculty() {
        boolean isValid = true;
        while (isValid) {
            System.out.println("1. View Specific Faculty\n2. View All Faculty\n3. Exit to Menu");
            System.out.print("Enter Your Choice: ");
            int choice = obj.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Faculty ID: ");
                    String id = obj.next();
                    faculty.getFacultyById(id).displayInfo();
                    System.out.println("-----------------------------");
                    break;
                case 2:
                    List<Faculty> facultyList = faculty.getAllFaculty();
                    for (Faculty f : facultyList) {
                        System.out.println("Faculty:   " + f.getName() + "\n" +
                                "ID:        " + f.getId() + "\n" +
                                "Email:     " + f.getEmail() + "\n" +
                                "Department:" + f.getDept());
                        System.out.println("-----------------------------");
                    }
                    break;
                case 3:
                    System.out.println("Exiting..");
                    isValid = false;
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    public void viewCourse() {
        boolean isValid = true;
        while (isValid) {
            System.out.println("1. View Specific Course\n2. View All Course\n3. Exit to Menu");
            System.out.print("Enter Your Choice: ");
            int choice = obj.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter Course ID: ");
                    String id = obj.next();
                    course.getCourseById(id).displayInfo();
                    break;
                case 2:
                    List<Course> courseList = course.getAllCourses();
                    for (Course c : courseList) {
                        System.out.println("Course Name:   " + c.getCourseName() + "\n" +
                                "ID:             " + c.getId() + "\n" +
                                "Faculty ID:     " + c.getFacultyId());
                        System.out.println("-----------------------------");
                    }
                    break;
                case 3:
                    System.out.println("Exiting..");
                    isValid = false;
                    break;
                default:
                    System.out.println("Invalid Choice");
            }
        }
    }

    public void viewEnroll() {
        boolean isValid = true;
        while (isValid) {
            System.out.println("1. View All Enrollement\n2. Exit to Menu");
            System.out.print("Enter Your Choice: ");
            int choice = obj.nextInt();
            switch (choice) {
                case 1:
                    List<Enrollment> enrollList = enroll.getAllEnrollments();
                    for (Enrollment e : enrollList) {
                        System.out.println("Student ID:   " + e.getStudentId() + "\n" +
                                "Course ID:    " + e.getCourseId());
                        System.out.println("-----------------------------");
                    }
                    break;
                case 2:
                    System.out.println("Exiting..");
                    isValid = false;
                    break;
                default:
                    System.out.println("Invalid Choie");
            }
        }
    }


}

