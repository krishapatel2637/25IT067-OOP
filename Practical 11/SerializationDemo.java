package P8;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

// Student class
class Student implements Serializable {

    String name;
    int age;

    // This field will NOT be saved
    transient String password;

    Student(String name, int age, String password) {
        this.name = name;
        this.age = age;
        this.password = password;
    }

    void display() {
        System.out.println("Name: " + name);
        System.out.println("Age: " + age);
        System.out.println("Password: " + password);
    }
}

// Main class
public class SerializationDemo {

    public static void main(String[] args) {

        try {

            // Create an array of Student objects
            Student[] students = {
                new Student("Dhriti", 18, "12345"),
                new Student("Krisha", 19, "abcde")
            };

            // Save objects to file
            FileOutputStream file =
                    new FileOutputStream("students.dat");

            ObjectOutputStream out =
                    new ObjectOutputStream(file);

            out.writeObject(students);

            out.close();
            file.close();

            System.out.println("Objects saved successfully.");

            // Read objects from file
            FileInputStream inputFile =
                    new FileInputStream("students.dat");

            ObjectInputStream in =
                    new ObjectInputStream(inputFile);

            Student[] loadedStudents =
                    (Student[]) in.readObject();

            in.close();
            inputFile.close();

            System.out.println("\nObjects loaded successfully.");

            // Display loaded objects
            for (Student student : loadedStudents) {
                student.display();
                System.out.println();
            }

        } catch (Exception e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}

