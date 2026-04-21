package ex1.dao;


import ex1.bean.Identifiable;
import java.util.ArrayList;
import java.util.List;

/**
 * Implémentation en mémoire de Dao<T> via ArrayList.
 */
public class ListDao<T extends Identifiable> implements Dao <T> {
    protected final List<T> items = new ArrayList<>();

   
    public void create(T obj) {
        items.add(obj);
    }

    public T update(T obj) {
        // Parcours pour remplacer l’objet existant portant le même ID
        for (int i = 0; i < items.size(); i++) {
            if (items.get(i).getId() == obj.getId()) {
                items.set(i, obj);
                return obj;
            }
        }
        return null;
    }

   
    public boolean delete(int id) {
        return items.removeIf(o -> o.getId() == id);
    }

   
    public T findById(int id) {
        return items.stream()
                    .filter(o -> o.getId() == id)
                    .findFirst()
                    .orElse(null);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(items); // copie défensive
    }
}
