package P7;

import java.util.List;

public class MiniBank {

    public static void main(String[] args) {

        // Create account with negative balance
        Account account =
                new Account("12345", -500);

        System.out.println(account);

        // Validate account
        List<String> errors =
                AnnotationValidator.validate(account);

        // Display errors
        if (errors.isEmpty()) {

            System.out.println("Account is valid!");

        } else {

            System.out.println("Validation Errors:");

            for (String error : errors) {

                System.out.println(error);
            }
        }
    }
}