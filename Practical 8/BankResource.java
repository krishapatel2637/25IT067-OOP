public class BankResource implements AutoCloseable {
    public BankResource() {
        System.out.println("Bank resource opened.");
    }
    public void performOperation() {
        System.out.println("Performing bank operation.");
    }
    @Override
    public void close() {
        System.out.println("Bank resource closed.");
    }
}