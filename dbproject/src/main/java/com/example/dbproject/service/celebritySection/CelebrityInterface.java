package com.example.dbproject.service.celebritySection;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CelebrityInterface<T> {

    Long save(T obj);


    T update(T obj, Long id);

    T delete(Long id);

    T get(Long id);

    List<T> getAll();


}
