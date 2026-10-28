public class MainRepository {
    public static void main(String[] args) {
        Repository<Account> r = new Repository<>();

        r.saveAll(
                new Account("101", "Krisha", 5000),
                new Account("102", "Riya", 6000),
                new Account("103", "Diya", 7000));

        System.out.println("Find Account:");
        System.out.println(r.findById("102"));

        System.out.println("\nAll Accounts:");
        for (Account a : r.findAll()) {
            System.out.println(a);
        }

        System.out.println("\nTotal Accounts: " + r.findAll().size());
    }
}