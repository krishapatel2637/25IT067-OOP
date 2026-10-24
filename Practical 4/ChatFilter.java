public class ChatFilter {

    public static void filter(String[] logs, String keyword) {

        int count = 0;

        System.out.println("Matching Messages:");

        for (String line : logs) {

            String[] parts = line.split(" ", 3);

            // Skip malformed lines
            if (parts.length < 3) {
                continue;
            }

            String time = parts[0];
            String user = parts[1];
            String message = parts[2];

            // Case-insensitive search
            if (message.toLowerCase().contains(keyword.toLowerCase())) {

                count++;

                System.out.println(time + " " + user + ": " + message);
            }
        }

        System.out.println("Total Matches: " + count);
    }
}