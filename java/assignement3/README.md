# Java HR Data Analysis Tool 📊

An interactive command-line tool built in Java for entering and analyzing employee data, showcasing the power of the Java Stream API for data processing.

## 📝 Description

This application provides an interactive command-line interface (CLI) for a user to input employee data, including their name, department, salary, and experience. Once the data entry is complete, the tool performs a series of data analysis tasks and generates a comprehensive report with key HR metrics. It serves as a practical demonstration of modern Java features for data manipulation and reporting.

## ✨ Features

-   **Interactive Data Entry**: A user-friendly CLI to dynamically add multiple employees.
-   **Input Validation**: Ensures that salary and experience are entered in the correct numerical format, reprompting the user if invalid data is entered.
-   **Comprehensive Analysis**: Generates a report that includes:
    -   A list of high-earning employees.
    -   Employees grouped by their department.
    -   The average salary calculated for each department.
    -   An advanced sort of employees by experience and salary.
-   **Stream API Powered**: Leverages the Java Stream API for efficient and readable data filtering, grouping, and sorting.
-   **Modular Code**: Well-structured with a clear separation of concerns into a data model, a reusable utility class, and the main application logic.

## 🏛️ Project Structure

The project is logically divided into three main files, promoting code reusability and maintainability.

-   `HRAnalysisTool.java`: The **main application class**. It runs in the console and performs the following tasks:
    -   Handles all user interaction for data entry.
    -   Collects and stores `Employee` objects in a list.
    -   Orchestrates the data analysis by calling utility methods.
    -   Formats and prints the final analysis report to the console.
-   `Employee.java`: The **data model (POJO)**. It defines the structure for an employee object, containing attributes for:
    -   `name`
    -   `department`
    -   `salary`
    -   `experience`
-   `StreamUtils.java`: A **reusable utility class**. It contains generic, static methods to perform common Stream API operations, making the main analysis logic cleaner.
    -   `filter()`: Filters a list based on a given condition.
    -   `groupBy()`: Groups elements of a list into a Map.
    -   `sort()`: Sorts a list based on a comparator.

## 🚀 How to Compile and Run

To run this project, you will need to have the **Java Development Kit (JDK)** installed on your system.

### 1. Place Files

Ensure `HRAnalysisTool.java`, `Employee.java`, and `StreamUtils.java` are all in the same directory.

### 2. Compile

Open a terminal or command prompt, navigate to the directory containing the files, and run the following command to compile the Java source files:

```bash
javac HRAnalysisTool.java Employee.java StreamUtils.java
```

Alternatively, on most systems, you can compile all `.java` files in the directory with:

```bash
javac *.java
```

### 3. Run

After successful compilation, run the main application with the following command:

```bash
java HRAnalysisTool
```

The program will then prompt you to start entering employee details.

