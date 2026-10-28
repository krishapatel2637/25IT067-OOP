import java.util.Scanner;

public class MiniBank {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankService bank = new BankService();

        while (true) {
            System.out.println("\n1.Open 2.Deposit 3.Withdraw");
            System.out.println("4.Transfer 5.Statement 6.List");
            System.out.println("7.Report 8.Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();

            try {
                switch (ch) {
                    case 1:
                        System.out.print("ID, name and balance: ");
                        int id = sc.nextInt();
                        String name = sc.next();
                        double bal = sc.nextDouble();
                        bank.openAccount(id, name, bal);
                        break;

                    case 2:
                        System.out.print("ID and amount: ");
                        bank.deposit(sc.nextInt(), sc.nextDouble());
                        break;

                    case 3:
                        System.out.print("ID and amount: ");
                        bank.withdraw(sc.nextInt(), sc.nextDouble());
                        break;

                    case 4:
                        System.out.print("From ID, To ID, Amount: ");
                        bank.transfer(sc.nextInt(), sc.nextInt(),
                                sc.nextDouble());
                        break;

                    case 5:
                        System.out.print("Enter ID: ");
                        bank.statement(sc.nextInt());
                        break;

                    case 6:
                        bank.listAccounts();
                        break;

                    case 7:
                        bank.report();
                        break;

                    case 8:
                        System.out.println("Thank you!");
                        sc.close();
                        return;

                    default:
                        System.out.println("Invalid choice");
                }
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}