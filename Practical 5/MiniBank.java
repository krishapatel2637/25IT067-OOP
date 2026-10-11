abstract class Account {
    String name;
    int accountNumber;
    long balance;
    Account(String name, int accountNumber, long balance) {
        this.name = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    abstract double interestRate();
    abstract boolean canWithdraw(long amount);
}
class SavingsAccount extends Account {
    long minBalance;
    SavingsAccount(String name, int accountNumber, long balance, long minBalance) {
        super(name, accountNumber, balance);
        this.minBalance = minBalance;
    }
    @Override double interestRate() {
        return 4.0;
    }
    @Override boolean canWithdraw(long amount) {
        return balance - amount >= minBalance;
    }
}
class CurrentAccount extends Account {
    long overdraftLimit;
    CurrentAccount(String name, int accountNumber, long balance, long overdraftLimit) {
        super(name, accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }
    @Override double interestRate() {
        return 0;
    }
    @Override boolean canWithdraw(long amount) {
        return balance - amount >= -overdraftLimit;
    }
}
class FixedDepositAccount extends Account {
    FixedDepositAccount(String name, int accountNumber, long balance) {
        super(name, accountNumber, balance);
    }
    @Override double interestRate() {
        return 7.0;
    }
    @Override boolean canWithdraw(long amount) {
        return false;
    }
}
public class MiniBank {
    public static void main(String[] args) {
        Account[] accounts = {
                new SavingsAccount("Rahul", 101, 50000, 10000),
                new CurrentAccount("Priya", 102, 20000, 15000),
                new FixedDepositAccount("Krisha", 103, 100000)
        };
        for (Account a : accounts) {
            System.out.println("Account Holder: " + a.name);
            System.out.println("Account Number: " + a.accountNumber);
            System.out.println("Balance: ₹" + a.balance);
            System.out.println("Interest Rate: " + a.interestRate() + "%");
            if (a instanceof FixedDepositAccount) {
                System.out.println("Note: Fixed Deposit is locked.");
            }
            System.out.println("-------------------------");
        }
        System.out.println("Withdrawal Tests:");
        for (Account a : accounts) {
            long amount = 30000;
            if (a.canWithdraw(amount)) {
                System.out.println(
                        a.name + " can withdraw ₹" + amount);
            } else {
                System.out.println(
                        a.name + " cannot withdraw ₹" + amount);
            }
        }
    }
}