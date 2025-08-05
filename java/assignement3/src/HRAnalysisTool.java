import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;


public class HRAnalysisTool {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();

        System.out.println("========================================");
        System.out.println("   Welcome to the HR Analysis Tool");
        System.out.println("========================================");
        System.out.println("\nPlease enter employee details. Type 'done' when you are finished.\n");

        while (true) {
            System.out.println("--- Adding New Employee ---");
            System.out.print("Enter name (or 'done' to finish): ");
            String name = scanner.nextLine();
            if (name.equalsIgnoreCase("done")) {
                break;
            }

            System.out.print("Enter department: ");
            String department = scanner.nextLine();

            double salary = 0;
            while (true) {
                System.out.print("Enter monthly salary: ");
                String salaryInput = scanner.nextLine();
                try {
                    salary = Double.parseDouble(salaryInput);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid number for salary.");
                }
            }
            
            int experience = 0;
            while (true) {
                System.out.print("Enter years of experience: ");
                String expInput = scanner.nextLine();
                try {
                    experience = Integer.parseInt(expInput);
                    break;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input. Please enter a valid integer for experience.");
                }
            }

            employees.add(new Employee(name, department, salary, experience));
            System.out.println("Employee added successfully!\n");
        }
        
        if (employees.isEmpty()) {
            System.out.println("\nNo employee data entered. Exiting.");
        } else {
            runAnalysisReport(employees);
        }

        scanner.close();
    }


    public static void runAnalysisReport(List<Employee> employees) {
        System.out.println("\n\n========================================");
        System.out.println("    HR Data Analysis Report");
        System.out.println("========================================");

        // 1. Filter employees earning > ₹50,000
        System.out.println("\n[ 1. Employees Earning > ₹50,000 ]");
        List<Employee> highEarners = StreamUtils.filter(employees, emp -> emp.getSalary() > 50000);
        if (highEarners.isEmpty()) {
            System.out.println("No employees earn more than ₹50,000.");
        } else {
            highEarners.forEach(System.out::println);
        }

        // 2. Group employees by department
        System.out.println("\n----------------------------------------");
        System.out.println("\n[ 2. Employees Grouped by Department ]");
        Map<String, List<Employee>> employeesByDept = StreamUtils.groupBy(employees, Employee::getDepartment);
        employeesByDept.forEach((dept, empList) -> {
            System.out.println("\n  Department: " + dept);
            empList.forEach(emp -> System.out.println("    -> " + emp));
        });

        // 3. Get average salary per department
        System.out.println("\n----------------------------------------");
        System.out.println("\n[ 3. Average Salary by Department ]");
        Map<String, Double> avgSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));
        avgSalaryByDept.forEach((dept, avgSalary) ->
                System.out.printf("  -> %-15s: ₹%.2f\n", dept, avgSalary)
        );

        // 4. Sort employees by experience, then by salary (descending)
        System.out.println("\n----------------------------------------");
        System.out.println("\n[ 4. Employees Sorted by Experience and Salary ]");
        System.out.println("(Sorted by experience ascending, then salary descending)");
        Comparator<Employee> byExperience = Comparator.comparingInt(Employee::getExperience);
        Comparator<Employee> bySalary = Comparator.comparingDouble(Employee::getSalary).reversed();
        List<Employee> sortedEmployees = StreamUtils.sort(employees, byExperience.thenComparing(bySalary));
        sortedEmployees.forEach(System.out::println);
        
        System.out.println("\n========================================");
        System.out.println("            End of Report");
        System.out.println("========================================");
    }
}