public class Main {
    public static void main(String[] args) throws Exception {
        Account a1 = new Account("AC001", "Riya", 4000);
        Account a2 = new Account("AC001", "Krisha", 7000);
        System.out.println(a1);
        System.out.println(a1.equals(a2));
        Customer.Address ad = new Customer.Address("ABC Road", "Anand", "388001");
        Customer c = new Customer("Riya", ad);
        Customer c2 = (Customer) c.clone();
        System.out.println(c2.getAddress().getCity());
        System.out.println(a1 instanceof Account);
    }
}