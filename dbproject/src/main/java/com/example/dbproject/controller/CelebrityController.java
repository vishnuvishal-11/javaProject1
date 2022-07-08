package com.example.dbproject.controller;
import com.example.dbproject.service.Celebrities;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.mapping.PropertyReferenceException;
import org.springframework.http.HttpStatus;
import javax.validation.Valid;
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
    Celebrities celebrityInterface;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CelebrityDto read(@PathVariable Long id) {
       return celebrityInterface.get(id);
    }

    @GetMapping("/")
    public Page<CelebrityDto> readAll(@RequestParam(value ="search" ,required = false) String search,@RequestParam(value = "offset",required = false,defaultValue = "0") int offset,
                                      @RequestParam(value = "pagesize",required = false,defaultValue = "5") int pageSize, @RequestParam(value = "field",required = false,defaultValue = "id")String field) {
//        CelebritySpecificationsBuilder builder = new CelebritySpecificationsBuilder();
//        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
//        Matcher matcher = pattern.matcher(search + ",");
//        while (matcher.find()) {
//            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
//        }
//
//        Specification<Celebrity> spec = builder.build();
//    public List<CelebrityDto> readAll(@RequestParam(required = false) String celebrity_name,
//                          @RequestParam(required = false) String celebrity_id,@RequestParam(required = false) String tags) {
        return celebrityInterface.getAll(search,offset,pageSize,field);
    }


    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@Valid @RequestBody CelebrityDto CelebrityDto) {
        Long id = celebrityInterface.save(CelebrityDto);
        if (id != null) return id;
        else
            throw new EntityNotFoundException("wrong input..");

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        celebrityInterface.delete(id);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.ACCEPTED)
    public CelebrityDto update(@RequestBody CelebrityDto CelebrityDto, @PathVariable Long id) {
        return (CelebrityDto) celebrityInterface.update(CelebrityDto, id);
    }

    @GetMapping("sort/{field}")
    @ResponseStatus(HttpStatus.OK)
    private List<CelebrityDto> getCelebritiesWithSort(@PathVariable String field) throws PropertyReferenceException {
        return celebrityInterface.findProductsWithSorting(field);

    }

//    @GetMapping("/pagination/{offset}/{pageSize}")
//    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
//    private Page<CelebrityDto> getCelebritiesWithPagination(@PathVariable int offset, @PathVariable int pageSize) {
//        return celebrityInterface.findProductsWithPagination(offset, pageSize);
//
//    }
    @GetMapping("/pagination/{offset}/{pageSize}/{field}")
    @ResponseStatus(HttpStatus.PARTIAL_CONTENT)
    private Page<CelebrityDto> getCelebritiesWithPaginationAndSort(@PathVariable int offset, @PathVariable int pageSize, @PathVariable String field) {
        return celebrityInterface.findProductsWithPaginationWithSort(offset, pageSize, field);

    }
}
