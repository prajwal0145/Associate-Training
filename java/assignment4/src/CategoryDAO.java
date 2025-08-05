import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Data Access Object for Category operations.
 */
public class CategoryDAO {
    public List<Category> getAllCategories() {
        List<Category> categories = new ArrayList<>();
        String sql = "SELECT * FROM categories";
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 1. Get a connection
            conn = DatabaseManager.getConnection();
            // 2. Create a statement
            stmt = conn.createStatement();
            // 3. Execute query
            rs = stmt.executeQuery(sql);
            // 4. Process the result set
            while (rs.next()) {
                Category category = new Category();
                category.setCategoryId(rs.getInt("category_id"));
                category.setCategoryName(rs.getString("category_name"));
                categories.add(category);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching categories: " + e.getMessage());
        } finally {
            // Ensure resources are closed to prevent leaks
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
            } catch (SQLException e) { e.printStackTrace(); }
            DatabaseManager.closeConnection(conn);
        }
        return categories;
    }
}
