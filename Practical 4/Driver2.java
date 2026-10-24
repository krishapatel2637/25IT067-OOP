public class Driver2 {

    public static void main(String[] args) {

        String template =
            "Hello {name}, your department is {dept} and your enrollment is {enroll}.";

        String[] names = {
            "name",
            "dept",
            "enroll"
        };

        String[] values = {
            "Dhriti",
            "IT",
            "25IT079"
        };

        String result = TemplateFiller.fill(template, names, values);

        System.out.println(result);
    }
}
