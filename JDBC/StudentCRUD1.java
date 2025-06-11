package JDBC;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class StudentCRUD1 extends JFrame {
	
	    private static final String URL = "jdbc:mysql://localhost:3306/testdb";
	    static final String USER = "root";
	    static final String PASS = "root@123"; 
	    
    private JTextField nameField, ageField, gradeField;
    private JTextArea outputArea;
    private Connection conn;

    public StudentCRUD1() {
        setTitle("Student Management System (No ID Field)");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        nameField = new JTextField();
        ageField = new JTextField();
        gradeField = new JTextField();

        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Age:"));
        inputPanel.add(ageField);
        inputPanel.add(new JLabel("Grade:"));
        inputPanel.add(gradeField);

        JPanel buttonPanel = new JPanel();
        JButton addBtn = new JButton("Add");
        JButton viewBtn = new JButton("View All");
        JButton updateBtn = new JButton("Update by Name");
        JButton deleteBtn = new JButton("Delete by Name");

        buttonPanel.add(addBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);

        outputArea = new JTextArea(10, 50);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(scrollPane, BorderLayout.SOUTH);

        try {
            conn = DriverManager.getConnection(
                "jdbc:mysql://localhost:3306/testdb", "root", "root@123"
            );
        } catch (SQLException e) {
            showMessage("Connection Failed: " + e.getMessage());
        }

        addBtn.addActionListener(e -> addStudent());
        viewBtn.addActionListener(e -> viewStudents());
        updateBtn.addActionListener(e -> updateStudent());
        deleteBtn.addActionListener(e -> deleteStudent());

        setVisible(true);
    }

    private void addStudent() {
        try (PreparedStatement stmt = conn.prepareStatement(
            "INSERT INTO students(name, age, grade) VALUES (?, ?, ?)")) {
            stmt.setString(1, nameField.getText());
            stmt.setInt(2, Integer.parseInt(ageField.getText()));
            stmt.setString(3, gradeField.getText());
            int rows = stmt.executeUpdate();
            showMessage("Added " + rows + " student.");
        } catch (SQLException e) {
            showMessage("Error: " + e.getMessage());
        }
    }

    private void viewStudents() {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM students")) {
            outputArea.setText("");
            while (rs.next()) {
                outputArea.append("ID: " + rs.getInt("id") +
                    ", Name: " + rs.getString("name") +
                    ", Age: " + rs.getInt("age") +
                    ", Grade: " + rs.getString("grade") + "\n");
            }
        } catch (SQLException e) {
            showMessage("Error: " + e.getMessage());
        }
    }

    private void updateStudent() {
        try (PreparedStatement stmt = conn.prepareStatement(
            "UPDATE students SET age=?, grade=? WHERE name=?")) {
            stmt.setInt(1, Integer.parseInt(ageField.getText()));
            stmt.setString(2, gradeField.getText());
            stmt.setString(3, nameField.getText());
            int rows = stmt.executeUpdate();
            showMessage("Updated " + rows + " student.");
        } catch (SQLException e) {
            showMessage("Error: " + e.getMessage());
        }
    }

    private void deleteStudent() {
        try (PreparedStatement stmt = conn.prepareStatement(
            "DELETE FROM students WHERE name=?")) {
            stmt.setString(1, nameField.getText());
            int rows = stmt.executeUpdate();
            showMessage("Deleted " + rows + " student.");
        } catch (SQLException e) {
            showMessage("Error: " + e.getMessage());
        }
    }

    private void showMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentCRUD1::new);
    }
}
