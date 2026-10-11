import java.util.*;
interface DiscountRule {
    double apply(double price);
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Double> prices = Arrays.asList(1000.0, 2000.0, 500.0);
        System.out.println("1. 10% Discount");
        System.out.println("2. 20% Discount");
        System.out.println("3. Flat 100 Discount");
        System.out.print("Enter choice: ");
        int choice = sc.nextInt();
        DiscountRule rule;
        if (choice == 1) {
            rule = price -> price * 0.90;
        } else if (choice == 2) {
            rule = price -> price * 0.80;
        } else {
            rule = price -> price - 100;
        }
        for (double price : prices) {
            System.out.println("Original: " + price);
            System.out.println("Final: " + rule.apply(price));
        }
        sc.close();
    }
}