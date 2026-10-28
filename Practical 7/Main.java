package P7;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface NotBlank {
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
@interface MaxLength {
    int value();
}

class SignupForm {

    @NotBlank
    @MaxLength(20)
    String name;

    @NotBlank
    @MaxLength(30)
    String email;

    SignupForm(String name, String email) {
        this.name = name;
        this.email = email;
    }
}

class Validator {

    static List<String> validate(Object obj) {

        List<String> errors = new ArrayList<>();

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {

            try {
                Object value = field.get(obj);

                if (field.isAnnotationPresent(NotBlank.class)) {

                    if (value == null ||
                        value.toString().trim().isEmpty()) {

                        errors.add(field.getName() + " cannot be empty");
                    }
                }

                if (field.isAnnotationPresent(MaxLength.class)) {

                    MaxLength max =
                            field.getAnnotation(MaxLength.class);

                    if (value != null &&
                        value.toString().length() > max.value()) {

                        errors.add(field.getName()
                                + " must be at most "
                                + max.value()
                                + " characters");
                    }
                }

            } catch (Exception e) {
                System.out.println(e);
            }
        }

        return errors;
    }
}

public class Main {

    public static void main(String[] args) {

        SignupForm form = new SignupForm(
                "",
                "thisemailaddressiswaytoolong123456789"
        );

        List<String> errors = Validator.validate(form);

        if (errors.isEmpty()) {

            System.out.println("Form is valid!");

        } else {

            System.out.println("Validation Errors:");

            for (String error : errors) {
                System.out.println(error);
            }
        }
    }
}

