public class Main {
    public static void main(String[] args) {
        Box<Integer> b = new Box<>();
        b.set(100);
        System.out.println("Box: " + b.get());

        Pair<String, Integer> p = new Pair<>("Krisha", 20);
        p.display();

        String[] names = { "Krisha", "Riya" };
        System.out.println("First: " + first(names));
    }

    static <T> T first(T[] arr) {
        return arr[0];
    }
}