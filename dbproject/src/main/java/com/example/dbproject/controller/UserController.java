package com.example.dbproject.controller;

import com.example.dbproject.dto.user.UserDto;
import com.example.dbproject.service.user.UserInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {
    @Autowired
    UserInterface userInterface;

    @GetMapping("/get/{id}")
    public ResponseEntity<UserDto> read(@PathVariable Long id) {
        try{
        return new ResponseEntity<>((UserDto) userInterface.get(id), HttpStatus.ACCEPTED);}
        catch (Exception e){
        return new ResponseEntity<>(HttpStatus.FORBIDDEN);}
    }

    @GetMapping("/get")
    public List<?> readAll() {
        return userInterface.getAll();
    }


    @PostMapping("/post")
    public ResponseEntity create(@RequestBody UserDto userDto) {
        try {
            return new ResponseEntity<>("" + userInterface.save(userDto), HttpStatus.ACCEPTED);
        } catch (EntityNotFoundException | ParseException e) {
            return new ResponseEntity<>("not a valid input", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<UserDto> delete(@PathVariable Long id) {
        try{
            return new ResponseEntity<>((UserDto) userInterface.delete(id), HttpStatus.ACCEPTED);}
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);}
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserDto> update(@RequestBody UserDto userDto, @PathVariable Long id) {
        try{
            return new ResponseEntity<>((UserDto) userInterface.update(userDto,id), HttpStatus.ACCEPTED);}
        catch (Exception e){
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);}
    }
}
