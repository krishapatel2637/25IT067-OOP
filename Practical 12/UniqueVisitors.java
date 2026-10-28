import java.util.*;

public class UniqueVisitors {
    public static void main(String[] args) {
        String[] users = {
                "Krisha", "Riya", "Krisha",
                "Diya", "Riya", "Aarav"
        };

        HashSet<String> set = new HashSet<>();
        LinkedHashSet<String> ordered = new LinkedHashSet<>();

        for (String user : users) {
            set.add(user);
            ordered.add(user);
        }

        System.out.println("Unique visitors: " + set.size());

        int count = 0;
        for (String user : ordered) {
            if (count == 3)
                break;
            System.out.println(user);
            count++;
        }
    }
}