import model.Faculty;
import model.Student;
public class Main {
    public static void main(String[] args) {
        Student s = new Student("S2025001", "Alice", "alice@college.com", "CS", 2);
        s.displayInfo();
        s.setEmail("alice.new@college.com");
        Faculty f=new Faculty("F2025001","Bob","bob@college.com","CSE");
        f.displayInfo();
    }
}
