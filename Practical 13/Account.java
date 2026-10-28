public class Account {
    @Id
    String id;
    String name;
    double balance;

    Account(String id, String name, double balance) {
        this.id = id;
        this.name = name;
        this.balance = balance;
    }

    public String toString() {
        return id + " " + name + " " + balance;
    }
}