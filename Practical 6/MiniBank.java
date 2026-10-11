interface Transactable {
    void deposit(long amount);
    boolean withdraw(long amount);
}
interface InterestBearing {
    double interestRate();
    default double yearlyInterest(long balance) {
        return balance * interestRate();
    }
}
interface Premium {
}
@FunctionalInterface
interface WithdrawRule {
    boolean allow(Account account, long amount);
}
class Account implements Transactable, InterestBearing, Premium {
    long balance;
    double rate;
    Account(long balance, double rate) {
        this.balance = balance;
        this.rate = rate;
    }
    public void deposit(long amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
    }
    public boolean withdraw(long amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
            return true;
        }
        System.out.println("Insufficient balance");
        return false;
    }
    public double interestRate() {
        return rate;
    }
    void showBalance() {
        System.out.println("Balance: " + balance);
    }
}
public class MiniBank {
    public static void main(String[] args) {
        Account account = new Account(10000, 0.05);
        System.out.println("Initial Balance:");
        account.showBalance();
        account.deposit(2000);
        account.showBalance();
        WithdrawRule rule1 = new WithdrawRule() {
            public boolean allow(Account account, long amount) {
                return amount > 0 && amount <= account.balance;
            }
        };
        System.out.println("\nAnonymous Class:");
        if (rule1.allow(account, 3000)) {
            account.withdraw(3000);
        }
        account.showBalance();
        WithdrawRule rule2 = (acc, amount) -> amount > 0 && amount <= acc.balance;
        System.out.println("\nLambda:");
        if (rule2.allow(account, 2000)) {
            account.withdraw(2000);
        }
        account.showBalance();
        System.out.println("\nYearly Interest: "
                + account.yearlyInterest(account.balance));
    }
}