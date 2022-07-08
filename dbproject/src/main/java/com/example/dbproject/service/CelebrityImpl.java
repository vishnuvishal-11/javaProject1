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

//        @PostConstruct
//    public void initDB() {
//            List langs=new ArrayList(Collections.singleton("englisg,tamil,hindi"));
//        List<CelebrityDto> celebrities = IntStream.rangeClosed(2, 10)
//                .mapToObj(i -> new CelebrityDto("celebrity_" + i,"about celebrity_" + i,langs, new Random().nextInt(10), new Random().nextInt(50000)))
//                .collect(Collectors.toList());
//         //   celebrityRepository.saveAll(products);
//    }

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
            String operationSetExpert = Joiner.on("|").join(SearchOperation.SIMPLE_OPERATION_SET);
            Pattern pattern = Pattern.compile("(\\w+?)(" + operationSetExpert + ")(\\p{Punct}?)(\\w+?)(\\p{Punct}?),");
            Matcher matcher = pattern.matcher(search + ",");
            while (matcher.find()) {
                builder.with(
                        matcher.group(1), matcher.group(2), matcher.group(4), matcher.group(3), matcher.group(5));
            }
            Specification<Celebrity> spec = builder.build();
         return converter.convertpageToDto(celebrityRepository.findAll(spec,PageRequest.of(offset, pagesize).withSort(Sort.by(Sort.Direction.ASC,field))));
//        else  return converter.convertListToDto(celebrityRepository.getReferencesByTags(name));
      //  else return converter.convertListToDto(celebrityRepository.getAllList(name,celebrity_id,tags));
    }}

    @Override
    public List<CelebrityDto> findProductsWithSorting(String field) {
        return converter.convertListToDto(celebrityRepository.findAll(Sort.by(Sort.Direction.ASC, field)));
    }

    @Override
    public Page<CelebrityDto> findProductsWithPagination(int offset, int pageSize) {
        return converter.convertpageToDto(celebrityRepository.findAll(PageRequest.of(offset, pageSize)));
    }

    @Override
    public Page<CelebrityDto> findProductsWithPaginationWithSort(int offset, int pageSize,String field) {
        return     converter.convertpageToDto(celebrityRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(Sort.Direction.ASC,field))));

    }
}
