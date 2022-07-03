package com.example.dbproject.dao.UserSection;

import java.util.List;

public interface UserDAO<T> {

    Long save(T obj);

    T update(T obj);

    void delete(Long id);

    T getById(Long id);

    List<T> getAll();

    Boolean existsById(Long id);
}
