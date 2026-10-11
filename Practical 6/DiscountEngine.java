import java.util.*;
public class DiscountEngine {
    public static void applyDiscount(List<Double> prices,
                                     DiscountRule rule) {
        for (double price : prices) {
            System.out.println("Original: " + price);
            System.out.println("Final: " + rule.apply(price));
        }
    }
}