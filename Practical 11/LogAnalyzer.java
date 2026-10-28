package P8;

import java.nio.file.*;
import java.io.IOException;
import java.util.List;

public class LogAnalyzer {

    public static void main(String[] args) {

        // Keyword to search
        String keyword = "ERROR";

        // P8/logs folder
        Path folder = Paths.get("P8", "logs");

        int totalLines = 0;
        int keywordLines = 0;

        try {

            // Get all text files from the logs folder
            DirectoryStream<Path> files =
                    Files.newDirectoryStream(folder, "*.txt");

            // Read each file
            for (Path file : files) {

                System.out.println("File: " + file.getFileName());

                // Get file size
                long size = Files.size(file);

                System.out.println("Size: " + size + " bytes");

                // Read all lines
                List<String> lines =
                        Files.readAllLines(file);

                // Count lines and keyword
                for (String line : lines) {

                    totalLines++;

                    if (line.contains(keyword)) {
                        keywordLines++;
                    }
                }

                System.out.println();
            }

            // Display final result
            System.out.println("Total lines: " + totalLines);

            System.out.println(
                    "Lines containing " + keyword + ": "
                    + keywordLines
            );

        } catch (IOException e) {

            System.out.println("Error: " + e.getMessage());
        }
    }
}

