
public class AccountWorker implements Runnable {

    private Account account;
    private int times;
    private long amount;
    private boolean safe;

    public AccountWorker(Account account, int times,
            long amount, boolean safe) {
        this.account = account;
        this.times = times;
        this.amount = amount;
        this.safe = safe;
    }

    @Override
    public void run() {
        System.out.println(
                Thread.currentThread().getName() + " started.");

        for (int i = 0; i < times; i++) {
            if (safe) {
                account.deposit(amount);
            } else {
                account.depositUnsafe(amount);
            }
        }

        System.out.println(
                Thread.currentThread().getName() + " finished.");
    }
}
