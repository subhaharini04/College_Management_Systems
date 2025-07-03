package model;

public class Faculty extends Person {
    private String dept;

    public Faculty(String id, String name, String email, String dept) {
        super(id, name, email);
        if (dept == null || dept.isEmpty()) {
            throw new IllegalArgumentException("Department cannot be empty!");
        }
        this.dept = dept;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    @Override
    public void displayInfo() {
        System.out.println("Faculty:   " + getName() + "\n" +
                "ID:        " + getId() + "\n" +
                "Email:     " + getEmail() + "\n" +
                "Department:" + dept);
    }
}

