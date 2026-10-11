class CinemaShow {
    private String title;
    private int seats;
    private final int capacity;
    private static int count = 0;
    CinemaShow(String title, int capacity) {
        this.title = title;
        this.capacity = capacity;
        this.seats = capacity;
    }
    CinemaShow(String title) {
        this(title, 100);
    }
    boolean book(int n) {
        if (n <= seats) {
            seats -= n;
            count += n;
            return true;
        } else {
            return false;
        }
    }
    void cancel(int n) {
        int booked = capacity - seats;
        if(n > booked) {
            n = booked;
        }
        seats += n;
        count -= n;
    }
    int getSeatsAvailable() {
        return seats;
    }
    static int getTotalBooked() {
        return count;
    }
    public static void main(String[] args) {
        CinemaShow obj = new CinemaShow("Movie", 50);
        System.out.println(obj.book(20));
        System.out.println(obj.getSeatsAvailable());
        System.out.println(obj.book(40));
        System.out.println(obj.getSeatsAvailable());
        obj.cancel(10);
        System.out.println(obj.getSeatsAvailable());
        System.out.println("Total Booked = " + CinemaShow.getTotalBooked());
    }
}