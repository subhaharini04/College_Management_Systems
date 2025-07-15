package dao;
import model.Course;
import model.Faculty;
import model.Student;
import util.DBConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CourseDAO {

    public void insertCourse(Course course){
        String sql="INSERT INTO courses(id,course_name,faculty_id) VALUES(?,?,?)";
        try(Connection conn=DBConnection.getConnection();
        PreparedStatement stmt= conn.prepareStatement(sql)) {
            stmt.setString(1,course.getId());
            stmt.setString(2, course.getCourseName());
            stmt.setString(3, course.getFacultyId());
            stmt.executeUpdate();
        }catch (SQLException e){
            System.out.println("Error in inserting Course: "+e.getMessage());
            e.printStackTrace();
        }
    }

    public static List<Course> getAllCourses(){
        List<Course> courseList=new ArrayList<>();
        String sql="SELECT * FROM courses";
        try(Connection conn = DBConnection.getConnection();
            PreparedStatement stmt= conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery()){
            while (rs.next()) {
                Course course = new Course(
                        rs.getString("id"),
                        rs.getString("course_name"),
                        rs.getString("faculty_id")
                );
                courseList.add(course);
            }
        }catch (SQLException e){
            System.err.println("Error fetching course list: " + e.getMessage());
        }
        return courseList;
    }

    public List<Course> courseByFaculty(String facultyId){
        List<Course> facultyList=new ArrayList<>();
        String sql="SELECT * FROM courses WHERE faculty_id=?";
        try(Connection conn=DBConnection.getConnection();
        PreparedStatement stmt= conn.prepareStatement(sql)){
            stmt.setString(1, facultyId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Course course = new Course(
                            rs.getString("id"),
                            rs.getString("course_name"),
                            rs.getString("faculty_id")
                    );
                    facultyList.add(course);
                }
            }
        }catch (SQLException e){
            System.out.println("Error in fetching faculty list: "+e.getMessage());
        }
        return facultyList;
    }

    public boolean updateFaculty(String id,String newFaculty){
        String sql="UPDATE courses SET faculty_id=? WHERE id=?";
        try(Connection conn=DBConnection.getConnection();
        PreparedStatement stmt= conn.prepareStatement(sql)){
            stmt.setString(1,newFaculty);
            stmt.setString(2,id);
            int rowUpdate= stmt.executeUpdate();
            return rowUpdate>0;
        }catch (SQLException e){
            System.out.println("Error in updating faculty: "+e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

}
