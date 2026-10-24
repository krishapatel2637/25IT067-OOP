public class Driver1 {

    public static void main(String[] args) {

        String[] logs = {
            "09:30 Neha Good Morning",
            "09:45 Rahul Java Practical Today",
            "10:00 Priya Good Luck",
            "10:30 Aman Network Lab"
        };

        String keyword = "Good";

        ChatFilter.filter(logs, keyword);
    }
}