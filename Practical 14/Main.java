public class Main {
    public static void main(String[] args) {
        StudentService service = new StudentService();

        service.addStudent("Krisha");
        service.addStudent("Riya");

        System.out.println("Total Students: " + service.getCount());
    }
}