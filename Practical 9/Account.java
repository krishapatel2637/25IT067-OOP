public class Account {
    private long balance;
    public Account(long balance) {
        this.balance = balance;
    }
    public void depositUnsafe(long amount) {
        long temp = balance;
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
        balance = temp + amount;
    }
    public synchronized void deposit(long amount) {
        balance += amount;
    }
    public synchronized boolean withdraw(long amount) {
        if (amount <= 0 || amount > balance) {
            return false;
        }
        balance -= amount;
        return true;
    }
    public synchronized long getBalance() {
        return balance;
    }
}