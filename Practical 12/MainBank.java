public class MainBank {
    public static void main(String[] args) {
        AccountStore store = new AccountStore();

        store.add(new Account("AC0001", "Krisha", 5000));
        store.add(new Account("AC0002", "Riya", 2000));
        store.add(new Account("AC0003", "Diya", 9000));

        System.out.println("Search Account:");
        System.out.println(store.get("AC0002"));

        System.out.println("\nAccounts Sorted by Balance:");
        for (Account a : store.sortedByBalance()) {
            System.out.println(a);
        }

        TransactionProcessor tp = new TransactionProcessor();

        for (Account a : store.getAll()) {
            tp.add(a);
        }

        System.out.println("\nShared Accounts: " + tp.accounts.size());
        System.out.println("Transaction History: " + store.history);
    }
}