import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Student {
    String name;
    int rollNumber;
    int[] marks;
    int totalMarks;
    double average;
    char grade;

    public Student(String name, int rollNumber, int[] marks) {
        this.name = name;
        this.rollNumber = rollNumber;
        this.marks = marks;
        calculateResults();
    }

    private void calculateResults() {
        totalMarks = 0;
        for (int mark : marks) {
            totalMarks += mark;
        }
        average = totalMarks / (double) marks.length;

        if (average >= 90) {
            grade = 'A';
        } else if (average >= 75) {
            grade = 'B';
        } else if (average >= 50) {
            grade = 'C';
        } else {
            grade = 'D';
        }
    }

    public void saveToFile() {
        try (FileWriter writer = new FileWriter("ReportCard_" + rollNumber + ".txt")) {
            writer.write("Student Report Card\n");
            writer.write("====================\n");
            writer.write("Name: " + name + "\n");
            writer.write("Roll Number: " + rollNumber + "\n");
            writer.write("Marks: ");
            for (int mark : marks) {
                writer.write(mark + " ");
            }
            writer.write("\nTotal Marks: " + totalMarks + "\n");
            writer.write("Average: " + String.format("%.2f", average) + "\n");
            writer.write("Grade: " + grade + "\n");
            System.out.println("Report card saved as ReportCard_" + rollNumber + ".txt");
        } catch (IOException e) {
            System.out.println("Error saving report card: " + e.getMessage());
        }
    }

    public void displayReportCard() {
        System.out.println("\nStudent Report Card");
        System.out.println("====================");
        System.out.println("Name: " + name);
        System.out.println("Roll Number: " + rollNumber);
        System.out.print("Marks: ");
        for (int mark : marks) {
            System.out.print(mark + " ");
        }
        System.out.println("\nTotal Marks: " + totalMarks);
        System.out.println("Average: " + String.format("%.2f", average));
        System.out.println("Grade: " + grade);
    }
}

public class ReportCardGenerator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        System.out.print("Enter roll number: ");
        int rollNumber = scanner.nextInt();

        System.out.print("Enter number of subjects: ");
        int numberOfSubjects = scanner.nextInt();

        int[] marks = new int[numberOfSubjects];
        System.out.println("Enter marks for " + numberOfSubjects + " subjects:");
        for (int i = 0; i < numberOfSubjects; i++) {
            System.out.print("Subject " + (i + 1) + ": ");
            marks[i] = scanner.nextInt();
        }

        Student student = new Student(name, rollNumber, marks);
        student.displayReportCard();

        System.out.print("\nDo you want to save the report card to a file? (yes/no): ");
        String saveToFile = scanner.next();

        if (saveToFile.equalsIgnoreCase("yes")) {
            student.saveToFile();
        }

        System.out.println("\nThank you for using the Student Report Card Generator!");
        scanner.close();
    }
}
