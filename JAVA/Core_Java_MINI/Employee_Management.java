import java.util.ArrayList;

// Main class
public class Employee_Management {
    public static void main(String[] args) {
        ArrayList<Employee> employees = new ArrayList<>();

        employees.add(new FullTimeEmployee("Alice", 101, 50000, 5000, 4000));
        employees.add(new PartTimeEmployee("Bob", 102, 20000, 20, 500));
        employees.add(new FullTimeEmployee("Charlie", 103, 55000, 6000, 4500));

        System.out.println("=== Employee Details ===");
        for (Employee emp : employees) {
            emp.display();
            System.out.println("----------------------------");
        }
    }
}


// Abstract class
abstract class Employee {
    private String name;
    private int empId;
    private double baseSalary;

    public Employee(String name, int empId, double baseSalary) {
        this.name = name;
        this.empId = empId;
        this.baseSalary = baseSalary;
    }

    public String getName() {
        return name;
    }

    public int getEmpId() {
        return empId;
    }

    public double getBaseSalary() {
        return baseSalary;
    }

    // Abstract method
    public abstract double calculateSalary();

    public void display() {
        System.out.println("ID: " + empId + ", Name: " + name + ", Base Salary: " + baseSalary);
    }
}

// Interface
interface BonusEligible {
    double calculateBonus();
}

// Full-time Employee
class FullTimeEmployee extends Employee implements BonusEligible {
    private double hra;
    private double da;

    public FullTimeEmployee(String name, int empId, double baseSalary, double hra, double da) {
        super(name, empId, baseSalary);
        this.hra = hra;
        this.da = da;
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + hra + da;
    }

    @Override
    public double calculateBonus() {
        return 0.10 * calculateSalary();
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Full-Time Salary: " + calculateSalary() + ", Bonus: " + calculateBonus());
    }
}

// Part-time Employee
class PartTimeEmployee extends Employee {
    private int hoursWorked;
    private double ratePerHour;

    public PartTimeEmployee(String name, int empId, double baseSalary, int hoursWorked, double ratePerHour) {
        super(name, empId, baseSalary);
        this.hoursWorked = hoursWorked;
        this.ratePerHour = ratePerHour;
    }

    @Override
    public double calculateSalary() {
        return getBaseSalary() + (hoursWorked * ratePerHour);
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Part-Time Salary: " + calculateSalary());
    }
}

