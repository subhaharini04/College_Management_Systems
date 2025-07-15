package dao;
import model.Course;
import model.Enrollment;
import model.Student;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class EnrollmentDAO {
    public void enrollStudents(String student_id,String course_id){
        String sql="INSERT INTO enrollement(student_id,course_id) VALUES (?,?) ";
        try(Connection conn= DBConnection.getConnection();
            PreparedStatement stmt= conn.prepareStatement(sql)){
            stmt.setString(1,student_id);
            stmt.setString(2,course_id);
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error in Enrolling: "+e.getMessage());
            e.printStackTrace();
        }
    }
//    getStudentsByCourse(courseId)
//    getCoursesByStudent(studentId)
//    deleteEnrollment(studentId, courseId)

    public List<Student> getStudentsByCourse(String courseId){
        String sql="SELECT * FROM enrollment WHERE course_id=?";
        try(Connection conn=DBConnection.getConnection();
        PreparedStatement stmt= conn.prepareStatement(sql)){
            stmt.setString(1,courseId);
            try(ResultSet rs=stmt.executeQuery()) {
                while (rs.next()) {
                    Enrollment enroll = new Enrollment(
                            rs.getString("student_id"),
                            rs.getString("course_id")
                    );
                }
            }
        }catch (SQLException e){
            System.out.println("Error in fetching:"+e.getMessage());
            e.printStackTrace();
        }
    }
}
