import java.util.ArrayList;

public class BankService {
    ArrayList<Account> accounts = new ArrayList<>();

    void openAccount(int id, String name, double balance) {
        accounts.add(new Account(id, name, balance));
        System.out.println("Account opened successfully");
    }

    Account find(int id) {
        for (Account a : accounts) {
            if (a.id == id)
                return a;
        }
        throw new IllegalArgumentException("Account not found");
    }

    void deposit(int id, double amount) {
        if (amount <= 0)
            throw new IllegalArgumentException("Invalid amount");

        find(id).balance += amount;
        System.out.println("Deposit successful");
    }

    void withdraw(int id, double amount) {
        Account a = find(id);

        if (amount <= 0 || amount > a.balance)
            throw new IllegalArgumentException("Invalid withdrawal");

        a.balance -= amount;
        System.out.println("Withdrawal successful");
    }

    void transfer(int from, int to, double amount) {
        Account a = find(from);
        Account b = find(to);

        if (amount <= 0 || amount > a.balance)
            throw new IllegalArgumentException("Invalid transfer");

        a.balance -= amount;
        b.balance += amount;
        System.out.println("Transfer successful");
    }

    void statement(int id) {
        Account a = find(id);
        System.out.println("Name: " + a.name);
        System.out.println("Balance: " + a.balance);
    }

    void listAccounts() {
        for (Account a : accounts)
            System.out.println(a.id + " " + a.name + " " + a.balance);
    }

    void report() {
        System.out.println("Total Accounts: " + accounts.size());
    }
}