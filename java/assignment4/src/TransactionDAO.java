import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class TransactionDAO {


    public void recordTransaction(int productId, String transactionType, int quantity) {
        Connection conn = null;
        try {
            // 1. Get a connection
            conn = DatabaseManager.getConnection();
            // Begin transaction by disabling auto-commit
            conn.setAutoCommit(false);

            // Get current product stock. This call internally follows the 4 JDBC steps.
            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.getProductById(productId);
            if (product == null) {
                throw new SQLException("Product with ID " + productId + " not found.");
            }


            int newStock = product.getStockQuantity();
            if ("OUT".equalsIgnoreCase(transactionType)) {
                if (newStock < quantity) {
                    throw new SQLException("Insufficient stock for transaction. Available: " + newStock + ", Required: " + quantity);
                }
                newStock -= quantity;
            } else if ("IN".equalsIgnoreCase(transactionType)) {
                newStock += quantity;
            } else {
                throw new SQLException("Invalid transaction type. Must be 'IN' or 'OUT'.");
            }


            String updateStockSql = "UPDATE products SET stock_quantity = ? WHERE product_id = ?";

            try (PreparedStatement updatePstmt = conn.prepareStatement(updateStockSql)) {

                updatePstmt.setInt(1, newStock);
                updatePstmt.setInt(2, productId);

                updatePstmt.executeUpdate();
            }


            String insertTransactionSql = "INSERT INTO transactions (product_id, transaction_type, quantity) VALUES (?, ?, ?)";
            try (PreparedStatement insertPstmt = conn.prepareStatement(insertTransactionSql)) {

                insertPstmt.setInt(1, productId);
                insertPstmt.setString(2, transactionType);
                insertPstmt.setInt(3, quantity);

                insertPstmt.executeUpdate();
            }


            conn.commit();
            System.out.println("Transaction recorded successfully.");

        } catch (SQLException e) {
            System.err.println("Transaction failed: " + e.getMessage());
            if (conn != null) {
                try {

                    System.err.println("Rolling back transaction...");
                    conn.rollback();
                    System.err.println("Transaction rolled back successfully.");
                } catch (SQLException ex) {
                    System.err.println("FATAL: Error during transaction rollback: " + ex.getMessage());
                }
            }
        } finally {
            if (conn != null) {
                try {

                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                DatabaseManager.closeConnection(conn);
            }
        }
    }
}
