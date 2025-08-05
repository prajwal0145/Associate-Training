import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Data Access Object for Transaction operations.
 * Demonstrates JDBC transaction management (commit/rollback).
 */
public class TransactionDAO {

    /**
     * Records a new transaction and updates product stock within a database transaction.
     */
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

            // Calculate new stock
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

            // --- First SQL command in transaction: Update product stock ---
            String updateStockSql = "UPDATE products SET stock_quantity = ? WHERE product_id = ?";
            // Using try-with-resources here for clarity within the larger transaction block
            try (PreparedStatement updatePstmt = conn.prepareStatement(updateStockSql)) {
                // 2. Create statement (handled by try-with-resources)
                updatePstmt.setInt(1, newStock);
                updatePstmt.setInt(2, productId);
                // 3. Execute query
                updatePstmt.executeUpdate();
            }

            // --- Second SQL command in transaction: Record the transaction ---
            String insertTransactionSql = "INSERT INTO transactions (product_id, transaction_type, quantity) VALUES (?, ?, ?)";
            try (PreparedStatement insertPstmt = conn.prepareStatement(insertTransactionSql)) {
                // 2. Create statement
                insertPstmt.setInt(1, productId);
                insertPstmt.setString(2, transactionType);
                insertPstmt.setInt(3, quantity);
                // 3. Execute query
                insertPstmt.executeUpdate();
            }

            // If all steps are successful, commit the changes
            conn.commit();
            System.out.println("Transaction recorded successfully.");

        } catch (SQLException e) {
            System.err.println("Transaction failed: " + e.getMessage());
            if (conn != null) {
                try {
                    // If any error occurs, roll back all changes made in this transaction
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
                    // Reset connection to its default auto-commit behavior
                    conn.setAutoCommit(true);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                // Finally, close the connection
                DatabaseManager.closeConnection(conn);
            }
        }
    }
}
