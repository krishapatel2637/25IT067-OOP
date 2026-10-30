import java.io.*;
@interface Id {
}
@interface Positive {
}
@interface Audit {
}
class BankException extends Exception {
    BankException(String msg) {
        super(msg);
    }
}
class Address implements Serializable {
    String city;
    Address(String city) {
        this.city = city;
    }
}
class Customer implements Cloneable, Serializable {
    String name;
    Address address;
    Customer(String name, String city) {
        this.name = name;
        address = new Address(city);
    }
    @Override
    public Customer clone() {
        return new Customer(name, address.city);
    }
}
interface Transactable {
    void deposit(double amount) throws BankException;

    void withdraw(double amount) throws BankException;
}
interface InterestBearing {
    double interest();
}
@Audit
abstract class Account implements Transactable, Serializable,
        Comparable<Account> {
    @Id
    String id;
    String name;
    @Positive
    protected double balance;
    Account(String id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }
    public synchronized void deposit(double amount) throws BankException {
        if (amount <= 0)
            throw new BankException("Invalid amount");
        balance += amount;
    }
    public synchronized void withdraw(double amount) throws BankException {
        if (amount <= 0 || amount > balance)
            throw new BankException("Invalid amount or insufficient balance");
        balance -= amount;
    }
    abstract double interest();
    public synchronized double getBalance() {
        return balance;
    }
    public int compareTo(Account a) {
        return id.compareTo(a.id);
    }
    public String toString() {
        return id + " | " + name + " | Rs." + balance;
    }
    public boolean equals(Object o) {
        return o instanceof Account a && id.equals(a.id);
    }
    public int hashCode() {
        return id.hashCode();
    }
}
class SavingsAccount extends Account {
    SavingsAccount(String id, String name, double balance) {
        super(id, name, balance);
    }

    double interest() {
        return balance * 0.04;
    }
}
class CurrentAccount extends Account {
    CurrentAccount(String id, String name, double balance) {
        super(id, name, balance);
    }
    double interest() {
        return 0;
    }
}