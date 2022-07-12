package com.example.dbproject.service;

import com.example.dbproject.dto.CelebrityDto;
import com.example.dbproject.dto.CelebrityConverter;
import com.example.dbproject.model.Price;
import com.example.dbproject.repository.CelebrityRepository;
import com.example.dbproject.specifications.CelebritySpecificationsBuilder;
import com.example.dbproject.specifications.SearchOperation;
import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import com.example.dbproject.model.Celebrity;

import javax.persistence.EntityNotFoundException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
public class CelebrityImpl implements Celebrities {
    @Autowired
    CelebrityRepository celebrityRepository;
    @Autowired
    private CelebrityConverter converter;


    @Override
    public Long save(CelebrityDto celebrityDto) {
        Celebrity celebrity = converter.convertToEntity(celebrityDto);
        celebrityRepository.save(celebrity);
        return celebrity.getId();
    }

    @Override
    public CelebrityDto update(CelebrityDto celebrityDto, Long id) {
        if (celebrityRepository.existsById(id)) {
            Celebrity celebrity = celebrityRepository.getReferenceById(id);
            celebrity.setName(celebrityDto.getName());
            celebrity.setLanguages_known(celebrityDto.getLanguages_known());
            celebrity.setAbout(celebrityDto.getAbout());
            celebrity.setDebut(celebrityDto.getDebut());
            celebrity.setDob(celebrityDto.getDob());
            celebrity.setGender(celebrityDto.getGender());
            celebrity.setFb_id(celebrityDto.getFb_id());
            celebrity.setInsta_id(celebrityDto.getInsta_id());
            celebrity.setBirth_place(celebrityDto.getBirth_place());
            celebrity.setMarital_status(celebrityDto.getMarital_status());
            celebrity.getPrices().clear();
            for (int i = 0; i < celebrityDto.getPlans().size(); i++) {
                LinkedHashMap linkedHashMap = (LinkedHashMap) celebrityDto.getPlans().get(i);
                ArrayList<Object> Values = new ArrayList<Object>(linkedHashMap.values());
                celebrity.getPrices().add(new Price(Values.get(0).toString(), (Integer) Values.get(1)));
            }
            celebrityRepository.save(celebrity);
            return converter.convertToDto(celebrity);

        } else {
            throw new EntityNotFoundException();
        }

    }

    @Override
    public void delete(Long id) {
        if (celebrityRepository.existsById(id)) {
            Celebrity celebrity = celebrityRepository.getReferenceById(id);
            celebrityRepository.deleteById(id);

        } else throw new EntityNotFoundException();
    }

    @Override
    public CelebrityDto get(Long id) {
        if (celebrityRepository.existsById(id)) {
            Celebrity celebrity = celebrityRepository.getReferenceById(id);
            return converter.convertToDto(celebrity);
        } else {
            throw new EntityNotFoundException();
        }
    }

    @Override
    public Page<CelebrityDto> getAll(String search,int offset,int pagesize,String field) {
        if(search==null)
        return converter.convertpageToDto(celebrityRepository.findAll(PageRequest.of(offset, pagesize).withSort(Sort.by(Sort.Direction.ASC,field))));
        else{
            CelebritySpecificationsBuilder builder = new CelebritySpecificationsBuilder();

            String operationSetExpert = Joiner.on("|")
                    .join(SearchOperation.SIMPLE_OPERATION_SET);
            Pattern pattern = Pattern.compile("(\\p{Punct}?)(\\w+?)(" + operationSetExpert
                            + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {builder.with(matcher.group(1), matcher.group(2), matcher.group(3),
                        matcher.group(5), matcher.group(4), matcher.group(6));
            }

            Specification<Celebrity> spec =  builder.build();
         return converter.convertpageToDto(celebrityRepository.findAll(spec,PageRequest.of(offset, pagesize).withSort(Sort.by(Sort.Direction.ASC,field))));
    }}

}
