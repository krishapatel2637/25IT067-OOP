import java.util.*;

public class Repository<T> {
    HashMap<String, T> data = new HashMap<>();

    T save(T entity) {
        try {
            for (var field : entity.getClass().getDeclaredFields()) {
                if (field.isAnnotationPresent(Id.class)) {
                    field.setAccessible(true);
                    String id = field.get(entity).toString();
                    data.put(id, entity);
                    return entity;
                }
            }
            throw new RuntimeException("ID not found");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    T findById(String id) {
        return data.get(id);
    }

    ArrayList<T> findAll() {
        return new ArrayList<>(data.values());
    }

    boolean delete(String id) {
        return data.remove(id) != null;
    }

    @SafeVarargs
    final void saveAll(T... items) {
        for (T item : items) {
            save(item);
        }
    }
}