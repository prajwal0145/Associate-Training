public class Transaction {
    private int transactionId;
    private int productId;
    private String transactionType;
    private int quantity;
    private java.sql.Timestamp transactionDate;


    public int getTransactionId() { return transactionId; }
    public void setTransactionId(int transactionId) { this.transactionId = transactionId; }
    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public String getTransactionType() { return transactionType; }
    public void setTransactionType(String transactionType) { this.transactionType = transactionType; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
    public java.sql.Timestamp getTransactionDate() { return transactionDate; }
    public void setTransactionDate(java.sql.Timestamp transactionDate) { this.transactionDate = transactionDate; }

    @Override
    public String toString() {
        return "Transaction [ID=" + transactionId + ", ProductID=" + productId +
                ", Type=" + transactionType + ", Quantity=" + quantity +
                ", Date=" + transactionDate + "]";
    }
}