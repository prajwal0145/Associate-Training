import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ProductDAO {


    public void addProduct(Product product) {
        String sql = "INSERT INTO products (product_name, category_id, price, stock_quantity) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 1. Get a connection to the database
            conn = DatabaseManager.getConnection();

            // 2. Create a statement object
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, product.getProductName());
            pstmt.setInt(2, product.getCategoryId());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getStockQuantity());

            // 3. Execute a SQL query
            pstmt.executeUpdate();

            // 4. Process the result set (Not applicable for INSERT)
            System.out.println("Product added successfully.");

        } catch (SQLException e) {
            System.err.println("Error adding product: " + e.getMessage());
        } finally {

            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) { e.printStackTrace(); }
            DatabaseManager.closeConnection(conn);
        }
    }


    public Product getProductById(int productId) {
        String sql = "SELECT * FROM products WHERE product_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        Product product = null;

        try {
            // 1. Get a connection to the database
            conn = DatabaseManager.getConnection();

            // 2. Create a statement object
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, productId);

            // 3. Execute a SQL query
            rs = pstmt.executeQuery();

            // 4. Process the result set
            if (rs.next()) {
                product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setPrice(rs.getDouble("price"));
                product.setStockQuantity(rs.getInt("stock_quantity"));
            }
        } catch (SQLException e) {
            System.err.println("Error getting product: " + e.getMessage());
        } finally {

            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) { e.printStackTrace(); }
            DatabaseManager.closeConnection(conn);
        }
        return product;
    }


    public void updateProduct(Product product) {
        String sql = "UPDATE products SET product_name = ?, category_id = ?, price = ?, stock_quantity = ? WHERE product_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            // 1. Get a connection
            conn = DatabaseManager.getConnection();
            // 2. Create a statement
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, product.getProductName());
            pstmt.setInt(2, product.getCategoryId());
            pstmt.setDouble(3, product.getPrice());
            pstmt.setInt(4, product.getStockQuantity());
            pstmt.setInt(5, product.getProductId());

            // 3. Execute query
            int affectedRows = pstmt.executeUpdate();

            // 4. Process result (optional, for feedback)
            if (affectedRows > 0) {
                System.out.println("Product updated successfully.");
            } else {
                System.out.println("Product not found for update.");
            }
        } catch (SQLException e) {
            System.err.println("Error updating product: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) { e.printStackTrace(); }
            DatabaseManager.closeConnection(conn);
        }
    }

    /**
     * Deletes a product by its ID.
     */
    public void deleteProduct(int productId) {
        String sql = "DELETE FROM products WHERE product_id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            // 1. Get a connection
            conn = DatabaseManager.getConnection();
            // 2. Create a statement
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, productId);
            // 3. Execute query
            int affectedRows = pstmt.executeUpdate();
            // 4. Process result (optional, for feedback)
            if (affectedRows > 0) {
                System.out.println("Product deleted successfully.");
            } else {
                System.out.println("Product not found for deletion.");
            }
        } catch (SQLException e) {
            System.err.println("Error deleting product: " + e.getMessage());
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) { e.printStackTrace(); }
            DatabaseManager.closeConnection(conn);
        }
    }

    /**
     * Displays products with pagination.
     */
    public List<Product> getProducts(int pageNumber, int pageSize) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT * FROM products LIMIT ? OFFSET ?";
        int offset = (pageNumber - 1) * pageSize;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            // 1. Get a connection
            conn = DatabaseManager.getConnection();
            // 2. Create a statement
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, pageSize);
            pstmt.setInt(2, offset);
            // 3. Execute query
            rs = pstmt.executeQuery();
            // 4. Process the result set
            while (rs.next()) {
                Product product = new Product();
                product.setProductId(rs.getInt("product_id"));
                product.setProductName(rs.getString("product_name"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setPrice(rs.getDouble("price"));
                product.setStockQuantity(rs.getInt("stock_quantity"));
                products.add(product);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching products: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) { e.printStackTrace(); }
            DatabaseManager.closeConnection(conn);
        }
        return products;
    }
}
