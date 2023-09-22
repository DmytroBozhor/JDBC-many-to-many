package org.example.dao;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface Dao<T> {

    Optional<T> get(long id);

    List<T> getAll();

    void save(T t);

    void update(T t, String[] params);

    void delete(T t);
}
