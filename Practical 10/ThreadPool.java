import java.util.concurrent.*;

public class ThreadPool {
    public static void main(String[] args) {

        ExecutorService pool = Executors.newFixedThreadPool(3);

        for (int i = 1; i <= 10; i++) {
            int n = i;

            pool.execute(() -> {
                System.out.println("Task " + n + " - "
                        + Thread.currentThread().getName());
            });
        }

        pool.shutdown();
    }
}
