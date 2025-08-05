// File: Product.java
public class Product {
    private int productId;
    private String productName;
    private int categoryId;
    private double price;
    private int stockQuantity;


    public Product() {}

    public int getProductId() { return productId; }
    public void setProductId(int productId) { this.productId = productId; }
    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }
    public int getCategoryId() { return categoryId; }
    public void setCategoryId(int categoryId) { this.categoryId = categoryId; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

    @Override
    public String toString() {
        return "Product [ID=" + productId + ", Name=" + productName +
                ", CategoryID=" + categoryId + ", Price=" + price +
                ", Stock=" + stockQuantity + "]";
    }
}


