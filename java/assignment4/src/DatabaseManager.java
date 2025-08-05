import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages the database connection for the application.
 */
public class DatabaseManager {
    // The database URL for a local MySQL instance.
    // Replace 'store_inventory' if you used a different database name.
    private static final String DB_URL = "jdbc:mysql://localhost:3306/store_inventory";

    // --- IMPORTANT ---
    // Replace these with your actual MySQL username and password.
    private static final String USER = "root";
    private static final String PASS = "root";

    static {
        try {
            // This line loads the MySQL JDBC driver into memory.
            // It's required for the DriverManager to know how to connect to a MySQL database.
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            // This error occurs if the MySQL Connector/J JAR file is not in the project's classpath.
            System.err.println("Fatal Error: MySQL JDBC Driver not found.");
            e.printStackTrace();
            // Terminate the application if the driver can't be loaded.
            System.exit(1);
        }
    }

    /**
     * Establishes and returns a new connection to the database.
     * @return A Connection object to the database.
     * @throws SQLException if a database access error occurs or the url is null.
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    /**
     * A utility method to safely close a database connection.
     * @param conn The connection to close.
     */
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
