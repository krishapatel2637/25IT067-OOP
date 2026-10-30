import java.util.*;
import java.util.concurrent.*;
import java.io.*;
import java.nio.file.*;
import java.lang.reflect.*;
class Repository<T> {
    private final Map<String, T> data = new ConcurrentHashMap<>();
    public void save(T obj) {
        try {
            Field f = obj.getClass().getSuperclass()
                    .getDeclaredField("id");
            f.setAccessible(true);
            data.put((String) f.get(obj), obj);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    public T find(String id) {
        return data.get(id);
    }
    public List<T> all() {
        return new ArrayList<>(data.values());
    }
    @SafeVarargs
    public final void saveAll(T... objects) {
        for (T obj : objects)
            save(obj);
    }
    public double average(List<? extends Account> list) {
        return list.stream().mapToDouble(Account::getBalance)
                .average().orElse(0);
    }
}
class BankService {
    static Repository<Account> repo = new Repository<>();
    static Set<String> visitors = new LinkedHashSet<>();
    static Map<String, Integer> words = new HashMap<>();
    static synchronized void transfer(Account from, Account to,
            double amount) throws Exception {
        if (from == to)
            throw new Exception("Same account");
        synchronized (BankService.class) {
            from.withdraw(amount);
            to.deposit(amount);
        }
        log("Transfer: " + from.id + " to " + to.id);
    }
    static void log(String msg) {
        try {
            Files.writeString(Path.of("bank.txt"), msg + "\n",
                    StandardOpenOption.CREATE, StandardOpenOption.APPEND);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    static void saveData() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("accounts.dat"))) {
            out.writeObject(repo.all());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    static void loadData() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("accounts.dat"))) {
            List<Account> list = (List<Account>) in.readObject();
            repo.saveAll(list.toArray(new Account[0]));
        } catch (Exception ignored) {
        }
    }
    static void countWords(String text) {
        for (String word : text.toLowerCase().split("\\s+"))
            words.put(word, words.getOrDefault(word, 0) + 1);
    }
    static void concurrentDeposit(Account a) {
        ExecutorService pool = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 2; i++)
            pool.submit(() -> {
                try {
                    a.deposit(100);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });
        pool.shutdown();
    }
    static class TaskQueue {
        Queue<String> queue = new LinkedList<>();
        synchronized void produce(String task) {
            queue.add(task);
            notify();
        }
        synchronized String consume() throws InterruptedException {
            while (queue.isEmpty())
                wait();
            return queue.poll();
        }
    }
    static void validate(Object obj) {
        for (Field f : obj.getClass().getSuperclass().getDeclaredFields()) {
            if (f.isAnnotationPresent(Positive.class)) {
                try {
                    f.setAccessible(true);
                    if (f.getDouble(obj) < 0)
                        System.out.println("Balance cannot be negative");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
    }
}