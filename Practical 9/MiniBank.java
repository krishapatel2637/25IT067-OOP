public class MiniBank {
    public static void main(String[] args)
            throws InterruptedException {
        int threadCount = 10;
        int times = 1000;
        long amount = 1;
        Thread[] threads = new Thread[threadCount];
        Account account1 = new Account(0);
        System.out.println("Without synchronization:");
        for (int i = 0; i < threadCount; i++) {
            AccountWorker worker = new AccountWorker(
                    account1, times, amount, false);
            threads[i] = new Thread(worker, "Thread-" + (i + 1));
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println(
                "Final balance: " + account1.getBalance());
        System.out.println("Expected balance: 10000");
        Account account2 = new Account(0);
        System.out.println("\nWith synchronization:");
        for (int i = 0; i < threadCount; i++) {
            AccountWorker worker = new AccountWorker(
                    account2, times, amount, true);
            threads[i] = new Thread(worker, "Thread-" + (i + 1));
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println(
                "Final balance: " + account2.getBalance());
        System.out.println("Expected balance: 10000");
        System.out.println("\nWithdrawal test:");
        boolean result = account2.withdraw(500);
        if (result) {
            System.out.println("Withdrawal successful: 500");
        } else {
            System.out.println("Withdrawal failed.");
        }
        System.out.println(
                "Balance after withdrawal: " + account2.getBalance());
    }
}