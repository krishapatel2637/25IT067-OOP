package P7;

@FunctionalInterface
public interface WithdrawRule {

    boolean allow(double amount);
}