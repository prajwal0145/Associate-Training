// StudentManager.java
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private final List<Student> students;

    public StudentManager() {
        this.students = new ArrayList<>();
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public Student findStudent(String name) {
        for (Student s : students) {
            if (s.getName().equalsIgnoreCase(name.trim())) {
                return s;
            }
        }
        return null; // Not found
    }

    public List<Student> getAllStudents() {
        return students;
    }
}