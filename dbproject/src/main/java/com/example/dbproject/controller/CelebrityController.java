package com.example.dbproject.controller;

import com.example.dbproject.service.celebritySection.CelebrityInterface;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
import java.util.List;
import com.example.dbproject.dto.celebritySection.CelebrityDto;

@RestController
@RequestMapping("/celebrity")
@Slf4j
public class CelebrityController {
@Autowired
CelebrityInterface celebrityInterface;

    @GetMapping("/get/{id}")
    public ResponseEntity<CelebrityDto> read(@PathVariable Long id) {
      return new ResponseEntity<>((CelebrityDto) celebrityInterface.get(id), HttpStatus.ACCEPTED);
    }

    @GetMapping("/get")
    public List<?> readAll() {
        return celebrityInterface.getAll();
    }
    

    @PostMapping("/post")
    public ResponseEntity create(@RequestBody CelebrityDto CelebrityDto) {
        try {
            return new ResponseEntity<>("" + celebrityInterface.save(CelebrityDto), HttpStatus.ACCEPTED);
        } catch (EntityNotFoundException e) {
            return new ResponseEntity<>("not a valid input", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/delete/{id}")
    public CelebrityDto delete(@PathVariable Long id) {
        return (CelebrityDto) celebrityInterface.delete(id);
    }

    @PutMapping("/update/{id}")
    public CelebrityDto update(@RequestBody CelebrityDto CelebrityDto, @PathVariable Long id) {
        return (CelebrityDto) celebrityInterface.update(CelebrityDto,id);
    }
}
