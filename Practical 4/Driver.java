public class Driver {

    public static void main(String[] args) {

        String[] passwords = {
            "Java123",
            "Coding@2026",
            "WELCOME1",
            "abc@12345"
        };

        for (String p : passwords) {

            System.out.println("Password : " + p);

            System.out.println("Length >=8 : " +
                    PasswordChecker.hasLength(p));

            System.out.println("Uppercase : " +
                    PasswordChecker.hasUppercase(p));

            System.out.println("Digit : " +
                    PasswordChecker.hasDigit(p));

            System.out.println("Special : " +
                    PasswordChecker.hasSpecial(p));

            System.out.println("Strength : " +
                    PasswordChecker.strength(p));

            System.out.println("-------------------------");
        }
    }
}
