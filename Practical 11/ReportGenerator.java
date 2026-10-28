package P8;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

public class ReportGenerator {

    public static void generate(Path folder) {

        long totalDeposits = 0;
        long totalWithdrawals = 0;

        try {

            DirectoryStream<Path> files =
                    Files.newDirectoryStream(folder, "*.log");

            for (Path file : files) {

                System.out.println("\nReading: "
                        + file.getFileName());

                BufferedReader reader =
                        Files.newBufferedReader(file);

                String line;

                while ((line = reader.readLine()) != null) {

                    System.out.println(line);

                    String[] parts = line.split(" ");

                    if (parts.length >= 2) {

                        long amount =
                                Long.parseLong(parts[1]);

                        if (parts[0].equals("DEPOSIT")) {
                            totalDeposits =
                                    totalDeposits + amount;
                        }

                        if (parts[0].equals("WITHDRAW")) {
                            totalWithdrawals =
                                    totalWithdrawals + amount;
                        }
                    }
                }

                reader.close();

                // File details
                long size = Files.size(file);

                System.out.println("File size: "
                        + size + " bytes");

                System.out.println("Last modified: "
                        + Files.getLastModifiedTime(file));
            }

            System.out.println("\n===== END OF DAY REPORT =====");
            System.out.println("Total Deposits: "
                    + totalDeposits);
            System.out.println("Total Withdrawals: "
                    + totalWithdrawals);

        } catch (IOException e) {

            System.out.println("Report error: "
                    + e.getMessage());
        }
    }
}