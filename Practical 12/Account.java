public class Account implements Comparable<Account> {
    String accountNumber;
    String name;
    double balance;

    Account(String accountNumber, String name, double balance) {
        this.accountNumber = accountNumber;
        this.name = name;
        this.balance = balance;
    }

    public int compareTo(Account a) {
        return accountNumber.compareTo(a.accountNumber);
    }

    public String toString() {
        return accountNumber + " " + name + " " + balance;
    }
}