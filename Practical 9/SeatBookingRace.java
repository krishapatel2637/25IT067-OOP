class SeatBooking {
    private int seatsLeft = 5;
    private int successfulBookings = 0;
    public void book() {
        if (seatsLeft > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
            seatsLeft--;
            successfulBookings++;
            System.out.println(
                    Thread.currentThread().getName() + " booked a seat.");
        }
    }
    public synchronized void bookSafe() {
        if (seatsLeft > 0) {
            seatsLeft--;
            successfulBookings++;
            System.out.println(
                    Thread.currentThread().getName() + " booked a seat.");
        }
    }
    public int getSeatsLeft() {
        return seatsLeft;
    }
    public int getSuccessfulBookings() {
        return successfulBookings;
    }
    public void reset() {
        seatsLeft = 5;
        successfulBookings = 0;
    }
}
public class SeatBookingRace {
    public static void main(String[] args)
            throws InterruptedException {
        SeatBooking booking = new SeatBooking();
        Thread[] threads = new Thread[10];
        System.out.println("Without synchronization:");
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(
                    () -> booking.book(), "User-" + (i + 1));
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println(
                "Successful bookings: " + booking.getSuccessfulBookings());
        System.out.println("Seats left: " + booking.getSeatsLeft());
        booking.reset();
        System.out.println("\nWith synchronization:");
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(
                    () -> booking.bookSafe(), "User-" + (i + 1));
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println(
                "Successful bookings: " + booking.getSuccessfulBookings());
        System.out.println("Seats left: " + booking.getSeatsLeft());
    }
}