package com.example.dbproject.dao.celebrity;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public interface CelebrityDAO<T> {

    Long save(T obj);

    T update(T obj);

    void delete(Long id);

    T getById(Long id);

    List<T> getAll();

    Boolean existsById(Long id);
}
