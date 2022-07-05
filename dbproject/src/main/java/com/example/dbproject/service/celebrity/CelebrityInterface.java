package com.example.dbproject.service.celebrity;

import org.springframework.stereotype.Component;

import java.util.List;


public interface CelebrityInterface<T> {

    Long save(T obj);


    T update(T obj, Long id);

    T delete(Long id);

    T get(Long id);

    List<T> getAll();


}
