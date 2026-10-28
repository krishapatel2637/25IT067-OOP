package P8;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main2 {

    public static void main(String[] args) {

        try {

            // Create logs folder
            Path logFolder =
                    Paths.get("P8", "logs");

            Files.createDirectories(logFolder);

            // Create file for transactions
            Path logFile =
                    logFolder.resolve("transactions.log");

            // Create accounts
            Account[] accounts = {

                new Account(
                        "A101",
                        "Khushi",
                        5000
                ),

                new Account(
                        "A102",
                        "Riya",
                        8000
                )
            };

            // Perform transactions
            accounts[0].deposit(2000);

            TransactionLog.append(
                    logFile,
                    "DEPOSIT 2000"
            );

            accounts[0].withdraw(1000);

            TransactionLog.append(
                    logFile,
                    "WITHDRAW 1000"
            );

            accounts[1].deposit(3000);

            TransactionLog.append(
                    logFile,
                    "DEPOSIT 3000"
            );

            accounts[1].withdraw(1500);

            TransactionLog.append(
                    logFile,
                    "WITHDRAW 1500"
            );

            // Save accounts
            Path accountFile =
                    Paths.get("P8", "accounts.dat");

            StatePersister.save(
                    accounts,
                    accountFile
            );

            // Load accounts
            Account[] loadedAccounts =
                    StatePersister.load(accountFile);

            System.out.println("\n===== LOADED ACCOUNTS =====");

            for (Account account : loadedAccounts) {
                System.out.println(account);
            }

            // Generate report
            ReportGenerator.generate(logFolder);

        } catch (Exception e) {

            System.out.println(
                    "Error: " + e.getMessage()
            );
        }
    }
}