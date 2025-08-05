import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages the database connection for the application.
 */
public class DatabaseManager {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/store_inventory";

    private static final String USER = "root";
    private static final String PASS = "root";

    static {
        try {

            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {

            System.err.println("Fatal Error: MySQL JDBC Driver not found.");
            e.printStackTrace();

            System.exit(1);
        }
    }


    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }


    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                // Log an error if closing the connection fails, but don't crash the app.
                System.err.println("Error closing connection: " + e.getMessage());
            }
        }
    }
}
