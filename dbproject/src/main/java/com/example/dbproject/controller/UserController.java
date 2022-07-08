package com.example.dbproject.controller;
import com.example.dbproject.dto.UserDto;
import com.example.dbproject.service.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    User userInterface;

    @GetMapping("/get/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDto read(@PathVariable Long id) {
        return userInterface.get(id);

    }

    @GetMapping("/get")
    public List<?> readAll() {
        return userInterface.getAll();
    }


    @PostMapping("/post")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Long create(@Validated @RequestBody UserDto userDto) throws ParseException, SQLIntegrityConstraintViolationException {
            return  userInterface.save(userDto);

    }

    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Long id) {
           userInterface.delete(id);

    }

    @PutMapping("/update/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserDto update(@RequestBody UserDto userDto, @PathVariable Long id) throws ParseException {
    try{
       return userInterface.update(userDto,id);

        } catch (ParseException e) {
            throw new ParseException("parse error",0);
        }
}



}