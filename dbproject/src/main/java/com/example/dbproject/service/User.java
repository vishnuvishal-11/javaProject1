package com.example.dbproject.service;

import com.example.dbproject.dto.UserDto;

import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.util.List;

public interface User {

    Long save(UserDto obj) throws ParseException, SQLIntegrityConstraintViolationException;

    UserDto update(UserDto obj, Long id) throws ParseException;

    void delete(Long id);

    UserDto get(Long id);

    List<UserDto> getAll();
}
