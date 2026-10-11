public class Account {
    private final String accountNumber;
    private String ownerName;
    private long balance;
    private boolean active;
    private static int accountCounter = 1;
    private static String generateAccountNumber() {
        return String.format("AC%04d", accountCounter++);
    }
    Account(String ownerName, long balance) {
        this.ownerName = ownerName;
        this.balance = balance;
        this.accountNumber = generateAccountNumber();
        this.active = true;
    }
    Account(String ownerName) {
        this(ownerName, 0);
    }
    public void deposit(long amount) {
        if (amount > 0)
            balance += amount;
    }
    public boolean withdraw(long amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public long getBalance() {
        return balance;
    }
    public boolean isActive() {
        return active;
    }
    public static void main(String[] args) {
        Customer c1 = new Customer("Krisha", "krisha@gmail.com", "9876543210");
        System.out.println("Customer ID : " + c1.getCustomerId());
        System.out.println("Customer Name : " + c1.getName());
        Account[] acc = new Account[3];
        acc[0] = new Account("Krisha", 5000);
        acc[1] = new Account("Riya", 3000);
        acc[2] = new Account("Aarav");
        acc[0].deposit(1000);
        acc[1].withdraw(500);
        acc[2].deposit(2000);
        acc[2].withdraw(1000);
        System.out.println();
        for (Account a : acc) {
            System.out.println("Account Number : " + a.getAccountNumber());
            System.out.println("Owner : " + a.getOwnerName());
            System.out.println("Balance : " + a.getBalance());
            System.out.println();
        }
    }
}