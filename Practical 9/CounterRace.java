class Counter {
    int count = 0;
    void increment() {
        count++;
    }
    synchronized void incrementSafe() {
        count++;
    }
}
public class CounterRace {
    static final int THREADS = 10;
    static final int ITERATIONS = 100000;
    public static void main(String[] args)
            throws InterruptedException {
        Counter counter = new Counter();
        Thread[] threads = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < ITERATIONS; j++) {
                    counter.increment();
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println("Without synchronization: " + counter.count);
        counter.count = 0;
        for (int i = 0; i < THREADS; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < ITERATIONS; j++) {
                    counter.incrementSafe();
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) {
            t.join();
        }
        System.out.println("With synchronization: " + counter.count);
    }
}