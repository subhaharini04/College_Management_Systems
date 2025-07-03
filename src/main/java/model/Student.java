package model;

public class Student extends Person { ;
   private String major;
   private int year;

    public Student(String id, String name, String email, String major, int year) {
        super(id, name, email);
        this.major = major;
        this.year = year;
    }

    public String getDept() {
        return major;
    }

    public void setDept(String dept) {
        this.major = dept;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
@Override
    public void displayInfo(){
        System.out.println("Student: " + getName() +
                "\nID: " + getId() +
                "\nEmail: " + getEmail() +
                "\nMajor: " + major +
                "\nYear: " + year);    }
}



