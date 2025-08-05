# Student Management System 🎓

A simple, console-based Java application for managing student records. This project is built with a focus on clean, Object-Oriented Programming (OOP) principles.

## 📝 Description

This application allows a user to perform basic administrative tasks for a student roster, including adding students, inputting their marks for a set of subjects, viewing individual performance reports, and saving all records to an external file.

## ✨ Features

- **Add Student**: Add a new student to the system with their name and age.
- **Enter Marks**: Input marks for three subjects (Math, Physics, Chemistry) for any existing student.
- **View Report**: Display a detailed report for a single student, including their marks, pass/fail status per subject, average score, and overall result.
- **Save All Reports**: Generate reports for all students and save them to a single text file.
- **User-Friendly CLI**: A straightforward command-line interface for easy navigation.

## 🏛️ Project Structure

This project follows the **Single Responsibility Principle**, where each class has one specific job. This makes the code cleaner, more maintainable, and easier to test.

- `StudentApp.java`: The main application **orchestrator**. It contains the `main` method, creates instances of all other classes, and controls the main application loop.
- `Student.java`: The **data model** (POJO) that represents a single student and contains logic related to a student's own data (like calculating their average).
- `StudentManager.java`: Acts as a repository for student data. Its sole responsibility is to manage the `ArrayList` of students (adding, finding, and retrieving them).
- `ReportManager.java`: Handles all logic related to generating report strings and saving data to files. It is stateless and operates on the data it's given.
- `AppUI.java`: Manages all console **user interface** logic. It handles displaying menus, printing messages, and capturing user input.

## 🚀 How to Compile and Run

To run this project, you will need to have the Java Development Kit (JDK) installed on your system.

### 1. Place Files

Ensure all five `.java` files are in the same directory.

### 2. Compile

Open a terminal or command prompt, navigate to the directory containing the files, and run the following command to compile all the Java source files:

```bash
javac *.java

### 2. Compile


Once compilation is successful, run the application from your terminal with the following command:

```bash
java StudentApp
