import model.Faculty;
import model.Student;
import util.DBConnection;
import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        System.getenv().forEach((k, v) -> {
            if (k.startsWith("DB_")) {
                System.out.printf("%s: %s%n", k, k.contains("PASS") ? "******" : v);
            }
        });
        try (Connection conn = DBConnection.getConnection()) {
            System.out.println("\n Database connection successful!");
        } catch (Exception e) {
            System.err.println("Connection failed: " + e.getMessage());
        }

    }
}
