package com.example.dbproject.service.user;

import java.text.ParseException;
import java.util.List;

public interface UserInterface<T> {

    Long save(T obj) throws ParseException;

    T update(T obj, Long id) throws ParseException;

    T delete(Long id);

    T get(Long id);

    List<?> getAll();
}
