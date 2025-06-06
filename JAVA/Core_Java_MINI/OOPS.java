// Main class to run the program
public class OOPS {
    public static void main(String[] args) {
        GraduateStudent student1 = new GraduateStudent("Srinivas", 101, "Data Science");
        student1.displayDetails();
    }
}


// Base class
class Student {
    private String name;
    private int rollNo;

    // Constructor
    public Student(String name, int rollNo) {
        this.name = name;
        this.rollNo = rollNo;
    }

    // Getter methods (Encapsulation)
    public String getName() {
        return name;
    }

    public int getRollNo() {
        return rollNo;
    }

    // Method
    public void displayDetails() {
        System.out.println("Name: " + name);
        System.out.println("Roll No: " + rollNo);
    }
}

// Subclass (Inheritance)
class GraduateStudent extends Student {
    private String specialization;

    public GraduateStudent(String name, int rollNo, String specialization) {
        super(name, rollNo); // Call to superclass constructor
        this.specialization = specialization;
    }

    // Override method
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Specialization: " + specialization);
    }
}

