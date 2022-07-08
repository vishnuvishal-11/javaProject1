package com.example.dbproject.service;


import com.example.dbproject.dto.CelebrityDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface Celebrities {

    Long save(CelebrityDto obj);


    CelebrityDto update(CelebrityDto obj, Long id);

    void delete(Long id);

    CelebrityDto get(Long id);

//    List<CelebrityDto> getAll(String field);

//    List<CelebrityDto> getAll(String name, String celebrity_id, String tags);

    List<CelebrityDto> findProductsWithSorting(String field);

    Page<CelebrityDto> findProductsWithPagination(int offset, int pageSize);

    Page<CelebrityDto> findProductsWithPaginationWithSort(int offset, int pageSize, String field);

    //    List<CelebrityDto> getAll(String name, String celebrity_id, String prices);
    Page<CelebrityDto> getAll(String name, int offset, int pagesize, String field);
}
