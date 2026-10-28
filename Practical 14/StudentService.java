import java.util.ArrayList;

public class StudentService {
    ArrayList<Student> students = new ArrayList<>();

    void addStudent(String name) {
        students.add(new Student(name));
    }

    int getCount() {
        return students.size();
    }
}