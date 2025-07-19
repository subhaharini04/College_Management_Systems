package dao;

import model.Course;
import model.Enrollment;
import model.Student;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EnrollmentDAO {
    public void enrollStudents(String student_id, String course_id) {
        String sql = "INSERT INTO enrollments(student_id,course_id) VALUES (?,?) ";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, student_id);
            stmt.setString(2, course_id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error in Enrolling: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Student> getStudentsByCourse(String courseId) {
        List<Student> studentList = new ArrayList<>();
        String sql = "SELECT s.* FROM students s join enrollments e ON s.id=e.student_id WHERE course_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, courseId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Student student = new Student(
                            rs.getString("id"),
                            rs.getString("name"),
                            rs.getString("email"),
                            rs.getString("major"),
                            rs.getInt("year"),
                            rs.getString("password")
                    );
                    studentList.add(student);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error in fetching:" + e.getMessage());
            e.printStackTrace();
        }
        return studentList;
    }

    public List<Course> getCoursesByStudent(String studentId) {
        List<Course> courseList = new ArrayList<>();
        String sql = "SELECT c.* FROM courses c join enrollments e ON c.id=e.course_id WHERE student_id=?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, studentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Course course = new Course(
                            rs.getString("id"),
                            rs.getString("course_name"),
                            rs.getString("faculty_id")
                    );
                    courseList.add(course);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error in fetching:" + e.getMessage());
            e.printStackTrace();
        }
        return courseList;
    }

    public boolean deleteEnrollment(String student_id , String course_id){
        String sql="DELETE FROM enrollments WHERE student_id=? AND course_id=?";
        try(Connection conn=DBConnection.getConnection();
        PreparedStatement stmt= conn.prepareStatement(sql)) {
            stmt.setString(1,student_id);
            stmt.setString(2,course_id);
            int rowDelete= stmt.executeUpdate();
            return rowDelete>0;
        }catch (SQLException e){
            System.out.println("Error in deleting: "+e.getMessage());
            e.printStackTrace();
        }
        return false;
    }

    public List<Enrollment> getAllEnrollments() {
        List<Enrollment> enrollmentList = new ArrayList<>();
        String sql = "SELECT * FROM enrollments";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Enrollment enrollment = new Enrollment(
                        rs.getString("student_id"),
                        rs.getString("course_id")
                );
                enrollmentList.add(enrollment);
            }
        } catch (SQLException e) {
            System.out.println("Error in fetching enrollments: " + e.getMessage());
            e.printStackTrace();
        }
        return enrollmentList;
    }

}
