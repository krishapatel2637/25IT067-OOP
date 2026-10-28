package P7;

import P7.annotation.Positive;
import P7.annotation.MaxLength;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class AnnotationValidator {

    public static List<String> validate(Object obj) {

        List<String> errors = new ArrayList<>();

        Field[] fields = obj.getClass().getDeclaredFields();

        for (Field field : fields) {

            try {

                field.setAccessible(true);

                Object value = field.get(obj);

                // Check @Positive
                if (field.isAnnotationPresent(Positive.class)) {

                    Positive positive =
                            field.getAnnotation(Positive.class);

                    if (value instanceof Number) {

                        double number =
                                ((Number) value).doubleValue();

                        if (number <= 0) {

                            errors.add(
                                    field.getName()
                                    + " "
                                    + positive.message()
                            );
                        }
                    }
                }

                // Check @MaxLength
                if (field.isAnnotationPresent(MaxLength.class)) {

                    MaxLength max =
                            field.getAnnotation(MaxLength.class);

                    if (value != null &&
                        value.toString().length() > max.value()) {

                        errors.add(
                                field.getName()
                                + " must be at most "
                                + max.value()
                                + " characters"
                        );
                    }
                }

            } catch (Exception e) {

                System.out.println("Error: "
                        + e.getMessage());
            }
        }

        return errors;
    }
}