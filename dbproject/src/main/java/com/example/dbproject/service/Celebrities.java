package com.example.dbproject.service;
import com.example.dbproject.dto.CelebrityDto;
import org.springframework.data.domain.Page;


public interface Celebrities {

    Long save(CelebrityDto obj);


    CelebrityDto update(CelebrityDto obj, Long id);

    void delete(Long id);

    CelebrityDto get(Long id);
    Page<CelebrityDto> getAll(String name, int offset, int pagesize, String field);



}
