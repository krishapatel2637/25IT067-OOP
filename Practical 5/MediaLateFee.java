abstract class Media {
    String title;
    int daysLate;
    Media(String title, int daysLate) {
        this.title = title;
        this.daysLate = daysLate;
    }
    abstract double lateFee();
}
class Book extends Media {
    Book(String title, int daysLate) {
        super(title, daysLate);
    }
    @Override double lateFee() {
        return daysLate * 2;
    }
}
class DVD extends Media {
    DVD(String title, int daysLate) {
        super(title, daysLate);
    }
    @Override double lateFee() {
        return daysLate * 5;
    }
}
class Magazine extends Media {
    Magazine(String title, int daysLate) {
        super(title, daysLate);
    }
    @Override double lateFee() {
        return daysLate * 3;
    }
}
public class MediaLateFee {
    public static void main(String[] args) {
        Media[] media = {
                new Book("Java Programming", 4),
                new DVD("Avengers", 3),
                new Magazine("Tech Today", 5),
                new Book("Data Structures", 2)
        };
        double totalFee = 0;
        for (Media m : media) {
            double fee = m.lateFee();
            System.out.println(
                    m.title + " - Days Late: " + m.daysLate +
                            " - Late Fee: ₹" + fee);
            totalFee += fee;
        }
        System.out.println("-------------------------");
        System.out.println("Total Late Fee: ₹" + totalFee);
    }
}