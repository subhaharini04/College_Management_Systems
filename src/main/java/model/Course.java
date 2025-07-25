package model;

public class Course {
    private final String id;
    private String courseName;
    private String facultyId;

    public Course(String id, String courseName, String facultyId) {
        if (id == null || id.isEmpty()) {
            throw new IllegalArgumentException("ID cannot be empty!");
        }
        this.id = id;
        setCourseName(courseName);
        this.facultyId=facultyId;
    }

    public Course(String id,String facultyId) {
        this.id=id;
        this.facultyId = facultyId;
    }

    public final String getId() {
        return id;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getFacultyId() {
        return facultyId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public void displayInfo(){
        System.out.println("Course Name:   " + getCourseName() + "\n" +
                "ID:             " + getId() + "\n" +
                "Faculty ID:     " + getFacultyId());
    }
}
