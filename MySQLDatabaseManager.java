import java.sql.*;

public class MySQLDatabaseManager {
    private static final String URL = "jdbc:mysql://localhost:3306/placement_system";
    private static final String USER = "root";      // Replace with your MySQL username
    private static final String PASSWORD = "password"; // Replace with your MySQL password

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void initializeDatabase() {
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement()) {
            System.out.println("Connected to MySQL database successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
