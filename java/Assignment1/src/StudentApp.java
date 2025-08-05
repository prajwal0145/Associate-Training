
import java.io.IOException;

public class StudentApp {
    
    private final StudentManager studentManager;
    private final ReportManager reportManager;
    private final AppUI ui;

    public StudentApp() {
        this.studentManager = new StudentManager();
        this.reportManager = new ReportManager();
        this.ui = new AppUI();
    }

    public void run() {
        int choice;
        do {
            ui.displayMenu();
            choice = ui.getIntInput();

            switch (choice) {
                case 1: addStudent(); break;
                case 2: enterMarks(); break;
                case 3: viewStudentReport(); break;
                case 4: saveAllReports(); break;
                case 5: ui.displayMessage("Exiting application."); break;
                default: ui.displayMessage("Invalid choice. Please try again.");
            }
        } while (choice != 5);
        
        ui.closeScanner();
    }

    private void addStudent() {
        String name = ui.getStringInput("Enter student name: ");
        ui.displayMessage("Enter student age: ");
        int age = ui.getIntInput();
        if (age <= 0) {
            ui.displayMessage("Age must be positive. Student not added.");
            return;
        }
        studentManager.addStudent(new Student(name, age));
        ui.displayMessage("Student '" + name + "' added.");
    }

    private void enterMarks() {
        String name = ui.getStringInput("Enter student name to enter marks for: ");
        Student s = studentManager.findStudent(name);
        if (s == null) {
            ui.displayMessage("Student not found.");
            return;
        }
        ui.getMarksForStudent(s);
        ui.displayMessage("Marks updated for " + s.getName() + ".");
    }

    private void viewStudentReport() {
        String name = ui.getStringInput("Enter student name to view report: ");
        Student s = studentManager.findStudent(name);
        if (s == null) {
            ui.displayMessage("Student not found.");
            return;
        }
        String report = reportManager.generateStudentReport(s);
        ui.displayMessage(report);
    }

    private void saveAllReports() {
        if (studentManager.getAllStudents().isEmpty()) {
            ui.displayMessage("No students to save reports for.");
            return;
        }
        String filename = ui.getStringInput("Enter filename (e.g., student_reports.txt): ");
        try {
            reportManager.saveAllReportsToFile(studentManager.getAllStudents(), filename);
            ui.displayMessage("All student reports saved to " + filename + ".");
        } catch (IOException e) {
            ui.displayMessage("Error saving reports: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        StudentApp app = new StudentApp();
        app.run();
    }
}