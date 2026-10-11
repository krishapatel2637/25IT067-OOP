public class Driver {
    public static void main(String[] args) {
        Point p[] = {
                new Point(1, 2),
                new Point(2, 3),
                new Point(1, 2),
                new Point(4, 5),
                new Point(2, 3)
        };
        int c = 0;
        for (int i = 0; i < p.length; i++) {
            boolean f = false;
            for (int j = 0; j < i; j++) {
                if (p[i].equals(p[j])) {
                    f = true;
                    break;
                }
            }
            if (!f)
                c++;
        }
        System.out.println("Distinct: " + c);
    }
}