// ReportManager.java
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ReportManager {

    public String generateStudentReport(Student student) {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Student Report ---\n");
        sb.append("Name: ").append(student.getName()).append(", Age: ").append(student.getAge()).append("\n");
        sb.append("Marks:\n");

        int[] marks = student.getMarks();
        for (int i = 0; i < Student.NUM_SUBJECTS; i++) {
            String markStr = (marks[i] == -1) ? "N/A" : String.valueOf(marks[i]);
            String status = (marks[i] == -1) ? "N/A" : (marks[i] >= Student.PASS_THRESHOLD ? "PASS" : "FAIL");
            sb.append(String.format("  - %s: %s (%s)\n", Student.subjectNames[i], markStr, status));
        }

        sb.append(String.format("Average: %.2f, Overall Status: %s\n", student.getAverage(), student.isOverallPassed() ? "PASS" : "FAIL"));
        sb.append("----------------------\n");
        return sb.toString();
    }

    public void saveAllReportsToFile(List<Student> students, String filename) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Student s : students) {
                writer.println(generateStudentReport(s));
            }
        }
    }
}