# JDBC Store Inventory Management 🏪

An interactive command-line tool for managing a store's inventory, demonstrating robust database interaction using Java and JDBC with a MySQL backend.

## 📝 Description

This application provides a console-based interface to manage products, categories, and stock levels in a relational database. It serves as a practical, hands-on example of using JDBC for database connectivity, implementing the Data Access Object (DAO) pattern for clean architecture, and ensuring data integrity through transaction management.

## ✨ Features

- **Full CRUD Operations**: Create, Read, Update, and Delete products with input validation.
- **MySQL Database Integration**: Connects to a MySQL database to persist all inventory data.
- **Secure Queries**: Uses `PreparedStatement` to prevent SQL injection attacks.
- **Transaction Management**: Employs JDBC transactions (commit/rollback) to ensure that stock updates and transaction logging are atomic operations, preventing data corruption.
- **Paginated Views**: Displays the product list in pages for better readability and performance.
- **DAO Architecture**: Organizes database logic cleanly using the Data Access Object pattern, separating business logic from data persistence logic.
- **Multi-Class Structure**: Well-structured with a clear separation of concerns into Models, DAOs, a Database Manager, and the Main Application.

## 🏛️ Project Structure

The project is logically divided into several classes, promoting code reusability and maintainability.

- `Main.java`: The **main application class**. It runs the console menu and handles all user interaction.
- `DatabaseManager.java`: A utility class responsible for establishing and closing the connection to the MySQL database.
- **Model Classes** (`Product.java`, `Category.java`, `Transaction.java`): The **data models (POJOs)** that define the structure for the application's core entities.
- **DAO Classes** (`ProductDAO.java`, `CategoryDAO.java`, `TransactionDAO.java`): The **Data Access Objects**. These classes contain all the SQL logic and JDBC calls required to interact with the database tables.

## 🛠️ Prerequisites

Before running this project, you need to have the following installed:

1.  **Java Development Kit (JDK)** (Version 8 or higher).
2.  **MySQL Server**.
3.  **MySQL Connector/J**: The official JDBC driver for MySQL.

## 🚀 How to Set Up and Run

### 1. Database Setup

First, you need to create the database and tables.
- Connect to your MySQL server.
- Execute the SQL commands in a `database_setup.sql` script to create the `store_inventory` database and its tables.

### 2. Configure Database Connection

- Open the `DatabaseManager.java` file.
- Update the `USER` and `PASS` constants with your actual MySQL username and password.

```java
private static final String USER = "your_username"; // <-- Change this
private static final String PASS = "your_password"; // <-- Change this


