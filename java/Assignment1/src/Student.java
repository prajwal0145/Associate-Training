// Student.java
public class Student {
    // Constants can remain static as they are class-level, not instance-level
    static final int NUM_SUBJECTS = 3;
    static final int PASS_THRESHOLD = 40;
    static final String[] subjectNames = {"Math", "Physics", "Chemistry"};

    String name;
    int age;
    int[] marks = new int[NUM_SUBJECTS];

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        for (int i = 0; i < NUM_SUBJECTS; i++) {
            marks[i] = -1; // -1 indicates mark not entered
        }
    }

    // --- Getters ---
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public int[] getMarks() {
        return marks;
    }
    
    // --- Business Logic Methods ---
    public double getAverage() {
        int sum = 0, count = 0;
        for (int mark : marks) {
            if (mark != -1) {
                sum += mark;
                count++;
            }
        }
        return count == 0 ? 0 : (double) sum / count;
    }

    public boolean isOverallPassed() {
        if (getAverage() < PASS_THRESHOLD) return false;
        for (int mark : marks) {
            if (mark != -1 && mark < PASS_THRESHOLD) return false;
        }
        return true;
    }
}