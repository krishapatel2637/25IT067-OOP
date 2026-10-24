import java.util.concurrent.*;

// Account class
class Account {
    int accountNumber;
    int balance;

    Account(int accountNumber, int balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
}

// Transaction Processor
class TransactionProcessor {

    ExecutorService pool = Executors.newFixedThreadPool(4);

    void submit(Runnable task) {
        pool.execute(task);
    }

    void stop() throws Exception {
        pool.shutdown();
        pool.awaitTermination(10, TimeUnit.SECONDS);
    }
}

// Shared Buffer
class TransactionBuffer {

    int[] buffer = new int[5];
    int count = 0;

    synchronized void add(int amount) throws Exception {

        while (count == buffer.length) {
            wait();
        }

        buffer[count] = amount;
        count++;

        System.out.println("Added transaction: " + amount);

        notify();
    }

    synchronized int remove() throws Exception {

        while (count == 0) {
            wait();
        }

        int amount = buffer[0];

        for (int i = 1; i < count; i++) {
            buffer[i - 1] = buffer[i];
        }

        count--;

        notify();

        return amount;
    }
}

public class MiniBank {

    // Deposit
    static void deposit(Account a, int amount) {
        a.balance += amount;

        System.out.println(
            "Deposited " + amount +
            " into Account " + a.accountNumber +
            " | Balance = " + a.balance
        );
    }

    // Withdraw
    static void withdraw(Account a, int amount) {

        if (a.balance >= amount) {
            a.balance -= amount;

            System.out.println(
                "Withdrawn " + amount +
                " from Account " + a.accountNumber +
                " | Balance = " + a.balance
            );
        } else {
            System.out.println("Not enough balance");
        }
    }

    // Deadlock-safe transfer
    static void transfer(Account a, Account b, int amount) {

        Account first;
        Account second;

        // Always lock smaller account number first
        if (a.accountNumber < b.accountNumber) {
            first = a;
            second = b;
        } else {
            first = b;
            second = a;
        }

        synchronized (first) {
            synchronized (second) {

                if (a.balance >= amount) {
                    a.balance -= amount;
                    b.balance += amount;

                    System.out.println(
                        "Transferred " + amount +
                        " from Account " + a.accountNumber +
                        " to Account " + b.accountNumber
                    );
                }
            }
        }
    }

    public static void main(String[] args) throws Exception {

        Account a = new Account(101, 1000);
        Account b = new Account(102, 1000);

        // 1. Thread Pool
        TransactionProcessor processor =
                new TransactionProcessor();

        // 2. Deposit and Withdrawal tasks
        processor.submit(() -> deposit(a, 500));
        processor.submit(() -> withdraw(a, 200));
        processor.submit(() -> deposit(b, 300));
        processor.submit(() -> withdraw(b, 100));

        processor.stop();

        System.out.println("\n--- Producer Consumer ---");

        // 3. Producer Consumer
        TransactionBuffer buffer = new TransactionBuffer();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    buffer.add(i * 100);
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++) {
                    int amount = buffer.remove();
                    System.out.println(
                        "Processed transaction: " + amount
                    );
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("\n--- Safe Transfer ---");

        // 4. Deadlock-safe transfers
        Thread t1 = new Thread(() -> {
            transfer(a, b, 100);
        });

        Thread t2 = new Thread(() -> {
            transfer(b, a, 200);
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("\nFinal Balances:");
        System.out.println("Account 101: " + a.balance);
        System.out.println("Account 102: " + b.balance);
    }
}
