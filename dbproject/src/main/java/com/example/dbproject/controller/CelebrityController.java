package com.example.dbproject.controller;
import com.example.dbproject.service.Celebrities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import javax.validation.Valid;

import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.example.dbproject.dto.CelebrityDto;

import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/celebrity")
@Slf4j
@Validated
public class CelebrityController {
    @Autowired
    Celebrities celebrity;

    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CelebrityDto read(@PathVariable Long id) {
       return celebrity.get(id);
    }

    @GetMapping(value ="/",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public Page<CelebrityDto> readAll(@RequestParam(value ="search" ,required = false) String search,@RequestParam(value = "offset",required = false,defaultValue = "0") int offset,
            @RequestParam(value = "pagesize",required = false,defaultValue = "5") int pageSize, @RequestParam(value = "field",required = false,defaultValue = "id")String field) {
        return celebrity.getAll(search,offset,pageSize,field);
    }


    @PostMapping(value ="/",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public Long create(@Valid @RequestBody CelebrityDto CelebrityDto) {
        Long id = celebrity.save(CelebrityDto);
        if (id != null) return id;
             else
            throw new EntityNotFoundException("wrong input..");

    }

    @DeleteMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @ResponseBody
    public void delete(@PathVariable Long id) {
        celebrity.delete(id);
    }

    @PutMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.ACCEPTED)
    @ResponseBody
    public CelebrityDto update(@RequestBody CelebrityDto CelebrityDto, @PathVariable Long id) {
        return (CelebrityDto) celebrity.update(CelebrityDto, id);
    }


}
