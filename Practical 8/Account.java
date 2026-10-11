import exception.BankException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;
import exception.AccountNotFoundException;
public class Account {
    private String accountNumber;
    private long balance;
    public Account(String accountNumber, long balance) {
        this.accountNumber = accountNumber;
        this.balance = balance;
    }
    public String getAccountNumber() {
        return accountNumber;
    }
    public long getBalance() {
        return balance;
    }
    public void deposit(long amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Deposit amount must be greater than zero.");
        }
        balance += amount;
        System.out.println("Deposit successful: " + amount);
    }
    public void withdraw(long amount)
            throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Withdrawal amount must be greater than zero.");
        }
        if (amount > balance) {
            throw new InsufficientFundsException(amount - balance);
        }
        balance -= amount;
        System.out.println("Withdrawal successful: " + amount);
    }
    public void transfer(Account to, long amount)
            throws BankException {
        try {
            if (to == null) {
                throw new AccountNotFoundException(
                        "Destination account not found.");
            }
            if (amount <= 0) {
                throw new InvalidAmountException(
                        "Transfer amount must be greater than zero.");
            }
            withdraw(amount);
            to.deposit(amount);
            System.out.println("Transfer successful: " + amount);
        } catch (BankException e) {
            System.out.println("Transfer failed: " + e.getMessage());
            throw e;
        } finally {
            System.out.println("Transfer operation completed.");
        }
    }
}