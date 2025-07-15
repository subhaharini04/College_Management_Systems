package model;

public class Enrollment {
    private String courseId;
    private String studentId;

    public Enrollment(String courseId, String studentId) {
        this.courseId = courseId;
        this.studentId = studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getStudentId() {
        return studentId;
    }

}
