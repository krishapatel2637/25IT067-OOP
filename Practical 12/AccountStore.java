import java.util.*;

public class AccountStore {
    HashMap<String, Account> accounts = new HashMap<>();
    ArrayList<String> history = new ArrayList<>();

    void add(Account a) {
        accounts.put(a.accountNumber, a);
        history.add("Added " + a.accountNumber);
    }

    Account get(String number) {
        return accounts.get(number);
    }

    ArrayList<Account> getAll() {
        return new ArrayList<>(accounts.values());
    }

    ArrayList<Account> sortedByBalance() {
        ArrayList<Account> list = getAll();

        list.sort((a, b) -> Double.compare(b.balance, a.balance));

        return list;
    }
}