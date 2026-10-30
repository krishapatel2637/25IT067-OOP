import java.util.*;
enum MenuOption {
    CREATE, DEPOSIT, WITHDRAW, TRANSFER, DISPLAY, EXIT
}
record BankInfo(String bank, String branch) {
}
public class MiniBank {
    static Scanner sc = new Scanner(System.in);
    static int id = 1001;
    public static void main(String[] args) {
        BankService.loadData();
        BankInfo info = new BankInfo("MiniBank", "Anand");
        System.out.println(info.bank() + " - " + info.branch());
        int choice;
        MenuOption option;
        do {
            System.out.println("\n1.Create  2.Deposit  3.Withdraw");
            System.out.println("4.Transfer  5.Display  6.Exit");
            System.out.print("Choice: ");
            choice = sc.nextInt();
            option = switch (choice) {
                case 1 -> MenuOption.CREATE;
                case 2 -> MenuOption.DEPOSIT;
                case 3 -> MenuOption.WITHDRAW;
                case 4 -> MenuOption.TRANSFER;
                case 5 -> MenuOption.DISPLAY;
                default -> MenuOption.EXIT;
            };
            try {
                switch (option) {
                    case CREATE -> {
                        System.out.print("Name: ");
                        String name = sc.next();
                        System.out.print("Opening balance: ");
                        double amount = sc.nextDouble();
                        System.out.print("1.Savings  2.Current: ");
                        int type = sc.nextInt();
                        Account a = type == 1
                                ? new SavingsAccount("A" + id++, name, amount)
                                : new CurrentAccount("A" + id++, name, amount);
                        BankService.repo.save(a);
                        System.out.println("Created: " + a);
                    }
                    case DEPOSIT, WITHDRAW -> {
                        System.out.print("Account ID: ");
                        Account a = BankService.repo.find(sc.next());
                        if (a == null)
                            throw new Exception("Not found");

                        System.out.print("Amount: ");
                        double amount = sc.nextDouble();
                        if (option == MenuOption.DEPOSIT)
                            a.deposit(amount);
                        else
                            a.withdraw(amount);
                        BankService.log("Transaction: " + a);
                        System.out.println("Successful!");
                    }
                    case TRANSFER -> {
                        System.out.print("From ID: ");
                        Account from = BankService.repo.find(sc.next());
                        System.out.print("To ID: ");
                        Account to = BankService.repo.find(sc.next());
                        if (from == null || to == null)
                            throw new Exception("Account not found");
                        System.out.print("Amount: ");
                        BankService.transfer(from, to, sc.nextDouble());
                        System.out.println("Transfer successful!");
                    }
                    case DISPLAY -> {
                        List<Account> list = BankService.repo.all();
                        Collections.sort(list);
                        for (Account a : list) {
                            System.out.println(a);
                            System.out.println("Interest: " + a.interest());
                            BankService.validate(a);
                        }
                        System.out.println("Average balance: " +
                                BankService.repo.average(list));
                    }
                    case EXIT -> {
                        BankService.saveData();
                        System.out.println("Thank you!");
                    }
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (option != MenuOption.EXIT);
    }
}