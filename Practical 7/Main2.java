package P7;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.reflect.Method;

// Create Run annotation
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@interface Run {
}

// Class containing test methods
class MyTests {

    @Run
    public void test1() {
        System.out.println("Test 1 is running");
    }

    @Run
    public void test2() {
        System.out.println("Test 2 is running");
    }

    public void normalMethod() {
        System.out.println("This method is not a test");
    }

    @Run
    public void test3() {
        System.out.println("Test 3 is running");
    }
}

// Main class
public class Main2 {

    public static void main(String[] args) {

        MyTests tests = new MyTests();

        int count = 0;

        // Get all methods
        Method[] methods = MyTests.class.getDeclaredMethods();

        // Check every method
        for (Method method : methods) {

            if (method.isAnnotationPresent(Run.class)) {

                try {
                    method.invoke(tests);
                    count++;

                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
        }

        System.out.println("Total tests ran: " + count);
    }
}


