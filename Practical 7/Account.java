package P7;

import P7.annotation.Id;
import P7.annotation.Positive;
import P7.annotation.MaxLength;

public class Account {

    @Id
    @MaxLength(10)
    private String accountNumber;

    @Positive
    private double balance;

    public Account(String accountNumber, double balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Account Number: " + accountNumber
                + ", Balance: " + balance;
    }

    @Override
    public boolean equals(Object obj) {

        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Account)) {
            return false;
        }

        Account other = (Account) obj;

        return accountNumber.equals(other.accountNumber);
    }

    @Override
    public int hashCode() {
        return accountNumber.hashCode();
    }
}