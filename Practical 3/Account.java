import java.util.Objects;
public class Account {
    String accountNumber;
    String ownerName;
    double balance;
    Account(String accountNumber, String ownerName, double balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }
    public String toString() {
        return accountNumber + " | " + ownerName + " | " + balance;
    }
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Account))
            return false;
        Account a = (Account) o;
        return accountNumber.equals(a.accountNumber);
    }
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}