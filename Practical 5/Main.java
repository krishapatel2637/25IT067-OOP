class Shape{
    double area(){
        return 0;
    }
}
class Circle extends Shape {
    double r = 5;
    double area() {
        return 3.14 * r * r;
    }
}
class Rectangle extends Shape {
    double l = 4, b = 6;
    double area() {
        return l * b;
    }
}
class Triangle extends Shape {
    double base = 3, height = 8;
    double area() {
        return 0.5 * base * height;
    }
}
public class Main {
    public static void main(String[] args) {
        Shape[] s = {
            new Circle(),
            new Rectangle(),
            new Triangle()
        };
        double total = 0;
        double largest = 0;
        for (int i = 0; i < s.length; i++) {
            double a = s[i].area();
            System.out.println("Area = " + a);
            total = total + a;
            if (a > largest) {
                largest = a;
            }
        }
        System.out.println("Total Area = " + total);
        System.out.println("Largest Area = " + largest);
    }
}
