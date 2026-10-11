import exception.BankException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;
public class MiniBank {
    public static void main(String[] args) {
        Account a1 = new Account("A101", 1000);
        Account a2 = new Account("A102", 500);
        System.out.println("----- WITHDRAWAL TEST -----");
        try {
            System.out.println("Initial balance: " + a1.getBalance());
            a1.withdraw(5000);
        } catch (InsufficientFundsException e) {
            System.out.println("Error: " + e.getMessage());
            System.out.println("Shortfall: " + e.getShortfall());
        } catch (InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            System.out.println("Withdrawal operation completed.");
        }
        System.out.println("\n----- DEPOSIT TEST -----");
        try {
            a1.deposit(500);
            System.out.println("Balance: " + a1.getBalance());
            a1.deposit(-100);
        } catch (InvalidAmountException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("\n----- TRANSFER TEST -----");
        try {
            a1.transfer(a2, 300);
        } catch (BankException e) {
            System.out.println("Error: " + e.getMessage());
        }
        System.out.println("A101 balance: " + a1.getBalance());
        System.out.println("A102 balance: " + a2.getBalance());
        System.out.println("\n----- TRY-WITH-RESOURCES TEST -----");
        try (BankResource resource = new BankResource()) {
            resource.performOperation();
        } catch (Exception e) {
            System.out.println("Resource error: " + e.getMessage());
        } finally {
            System.out.println("Resource operation completed.");
        }
    }
}