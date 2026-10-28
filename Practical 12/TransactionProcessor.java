import java.util.concurrent.ConcurrentHashMap;

public class TransactionProcessor {
    ConcurrentHashMap<String, Account> accounts = new ConcurrentHashMap<>();

    void add(Account a) {
        accounts.put(a.accountNumber, a);
    }

    Account get(String number) {
        return accounts.get(number);
    }
}