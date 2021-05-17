package ua.edu.j2ee.shoestore.dao;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {

    List<T> getAll();
    T get(int id);
    void save(T t);
    void update(T t);

}
