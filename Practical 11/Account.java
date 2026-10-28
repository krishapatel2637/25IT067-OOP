package P8;

import java.io.Serializable;

public class Account implements Serializable {

    private static final long serialVersionUID = 1L;

    String accountNumber;
    String owner;
    long balance;

    public Account(String accountNumber, String owner, long balance) {
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = balance;
    }

    public void deposit(long amount) {
        balance = balance + amount;
    }

    public void withdraw(long amount) {
        balance = balance - amount;
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber
                + ", Owner: " + owner
                + ", Balance: " + balance;
    }
}
