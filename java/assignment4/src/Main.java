import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;


public class Main{

    private static final ProductDAO productDAO = new ProductDAO();
    private static final CategoryDAO categoryDAO = new CategoryDAO();
    private static final TransactionDAO transactionDAO = new TransactionDAO();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            printMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine(); // consume the leftover newline character

                switch (choice) {
                    case 1:
                        addProduct();
                        break;
                    case 2:
                        updateProduct();
                        break;
                    case 3:
                        deleteProduct();
                        break;
                    case 4:
                        viewProducts();
                        break;
                    case 5:
                        recordTransaction();
                        break;
                    case 6:
                        viewCategories();
                        break;
                    case 7:
                        System.out.println("Exiting application.");
                        scanner.close();
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 7.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
            }
        }
    }

    private static void printMenu() {
        System.out.println("\n--- Store Inventory Management ---");
        System.out.println("1. Add Product");
        System.out.println("2. Update Product");
        System.out.println("3. Delete Product");
        System.out.println("4. View Products (Paginated)");
        System.out.println("5. Record Stock Transaction (IN/OUT)");
        System.out.println("6. View All Categories");
        System.out.println("7. Exit");
        System.out.print("Enter your choice: ");
    }

    private static void addProduct() {
        try {
            Product product = new Product();
            System.out.print("Enter Product Name: ");
            product.setProductName(scanner.nextLine());

            viewCategories();
            System.out.print("Enter Category ID: ");
            product.setCategoryId(scanner.nextInt());

            System.out.print("Enter Price: ");
            product.setPrice(scanner.nextDouble());

            System.out.print("Enter Stock Quantity: ");
            product.setStockQuantity(scanner.nextInt());
            scanner.nextLine();

            productDAO.addProduct(product);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input type. Please check your entries and try again.");
            scanner.nextLine();
        }
    }

    private static void updateProduct() {
        try {
            System.out.print("Enter Product ID to update: ");
            int productId = scanner.nextInt();
            scanner.nextLine();

            Product product = productDAO.getProductById(productId);
            if (product == null) {
                System.out.println("Product not found.");
                return;
            }

            System.out.print("Enter new Product Name (current: " + product.getProductName() + "): ");
            product.setProductName(scanner.nextLine());

            viewCategories();
            System.out.print("Enter new Category ID (current: " + product.getCategoryId() + "): ");
            product.setCategoryId(scanner.nextInt());

            System.out.print("Enter new Price (current: " + product.getPrice() + "): ");
            product.setPrice(scanner.nextDouble());

            System.out.print("Enter new Stock Quantity (current: " + product.getStockQuantity() + "): ");
            product.setStockQuantity(scanner.nextInt());
            scanner.nextLine();

            product.setProductId(productId);
            productDAO.updateProduct(product);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input type. Please check your entries and try again.");
            scanner.nextLine();
        }
    }

    private static void deleteProduct() {
        try {
            System.out.print("Enter Product ID to delete: ");
            int productId = scanner.nextInt();
            scanner.nextLine();
            productDAO.deleteProduct(productId);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input type. Please enter a number.");
            scanner.nextLine();
        }
    }

    private static void viewProducts() {
        try {
            System.out.print("Enter page number: ");
            int page = scanner.nextInt();
            System.out.print("Enter page size (number of products per page): ");
            int size = scanner.nextInt();
            scanner.nextLine();

            List<Product> products = productDAO.getProducts(page, size);
            if (products.isEmpty()) {
                System.out.println("No products found on this page.");
            } else {
                System.out.println("\n--- Products (Page " + page + ") ---");
                for (Product p : products) {
                    System.out.println(p);
                }
            }
        } catch (InputMismatchException e) {
            System.out.println("Invalid input type. Please enter numbers.");
            scanner.nextLine();
        }
    }

    private static void recordTransaction() {
        try {
            System.out.print("Enter Product ID for transaction: ");
            int productId = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Transaction Type (IN/OUT): ");
            String type = scanner.nextLine().toUpperCase();

            System.out.print("Enter Quantity: ");
            int quantity = scanner.nextInt();
            scanner.nextLine();

            if (!"IN".equals(type) && !"OUT".equals(type)) {
                System.out.println("Invalid transaction type. Must be 'IN' or 'OUT'.");
                return;
            }
            if (quantity <= 0) {
                System.out.println("Quantity must be a positive number.");
                return;
            }

            transactionDAO.recordTransaction(productId, type, quantity);
        } catch (InputMismatchException e) {
            System.out.println("Invalid input type. Please check your entries and try again.");
            scanner.nextLine();
        }
    }

    private static void viewCategories() {
        List<Category> categories = categoryDAO.getAllCategories();
        System.out.println("\n--- Available Categories ---");
        for (Category c : categories) {
            System.out.println(c);
        }
        System.out.println("--------------------------");
    }
}