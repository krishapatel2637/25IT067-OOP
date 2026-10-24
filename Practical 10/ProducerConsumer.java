class Buffer {

    int number;
    boolean empty = true;

    synchronized void produce(int n) throws Exception {

        while (!empty)
            wait();

        number = n;
        empty = false;

        System.out.println("Produced: " + n);

        notify();
    }

    synchronized void consume() throws Exception {

        while (empty)
            wait();

        System.out.println("Consumed: " + number);

        empty = true;

        notify();
    }
}

public class ProducerConsumer {
    public static void main(String[] args) {

        Buffer b = new Buffer();

        Thread producer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++)
                    b.produce(i);
            } catch (Exception e) {
                System.out.println(e);
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                for (int i = 1; i <= 5; i++)
                    b.consume();
            } catch (Exception e) {
                System.out.println(e);
            }
        });

        producer.start();
        consumer.start();
    }
}
