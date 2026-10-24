public class PasswordChecker {

    public static boolean hasLength(String password) {
        return password.length() >= 8;
    }

    public static boolean hasUppercase(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isUpperCase(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasDigit(String password) {
        for (int i = 0; i < password.length(); i++) {
            if (Character.isDigit(password.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static boolean hasSpecial(String password) {
        String special = "!@#$%^&*()_+-=[]{}|;:',.<>?/";

        for (int i = 0; i < password.length(); i++) {
            if (special.indexOf(password.charAt(i)) != -1) {
                return true;
            }
        }
        return false;
    }

    public static String strength(String password) {

        int count = 0;

        if (hasLength(password)) {
            count++;
        }

        if (hasUppercase(password)) {
            count++;
        }

        if (hasDigit(password)) {
            count++;
        }

        if (hasSpecial(password)) {
            count++;
        }

        if (count <= 1) {
            return "Weak";
        } else if (count <= 3) {
            return "Medium";
        } else {
            return "Strong";
        }
    }
}