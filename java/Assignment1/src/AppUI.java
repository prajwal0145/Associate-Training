
import java.util.InputMismatchException;
import java.util.Scanner;

public class AppUI {
    private final Scanner sc;

    public AppUI() {
        this.sc = new Scanner(System.in);
    }

    public void displayMenu() {
        System.out.println("\n--- Student App Menu ---");
        System.out.println("1. Add Student");
        System.out.println("2. Enter Marks");
        System.out.println("3. View Student Report");
        System.out.println("4. Save All Reports to File");
        System.out.println("5. Exit");
        System.out.print("Enter choice: ");
    }

    public int getIntInput() {
        while (true) {
            try {
                int value = sc.nextInt();
                sc.nextLine(); 
                return value;
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a number: ");
                sc.nextLine(); 
            }
        }
    }

    public String getStringInput(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }
    
    public void getMarksForStudent(Student student) {
        System.out.println("Entering marks for " + student.getName() + ":");
        for (int i = 0; i < Student.NUM_SUBJECTS; i++) {
            System.out.print("Enter marks for " + Student.subjectNames[i] + " (0-100): ");
            int mark = getIntInput();
            while (mark < 0 || mark > 100) {
                System.out.print("Invalid mark. Must be 0-100. Re-enter: ");
                mark = getIntInput();
            }
            student.getMarks()[i] = mark;
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
    
    public void closeScanner() {
        sc.close();
    }
}