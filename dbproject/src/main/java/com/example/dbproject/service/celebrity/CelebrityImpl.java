package com.example.dbproject.service.celebrity;

import com.example.dbproject.dao.celebrity.CelebrityDAO;
import com.example.dbproject.dto.celebrity.CelebrityDto;
import com.example.dbproject.model.celebrity.Celebrity;
import com.example.dbproject.model.celebrity.Price;
import com.example.dbproject.repository.celebrity.CelebrityRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.*;

@Slf4j
@Service
public class CelebrityImpl<T> implements CelebrityInterface<T> {

    @Autowired
    CelebrityDAO celebrityDAO;
    @Autowired
    CelebrityRepository celebrityRepository;

    @Override
    public Long save(T obj) {
        CelebrityDto CelebrityDto = (CelebrityDto) obj;

        Celebrity celebrity = new Celebrity(CelebrityDto.getName(), CelebrityDto.getAbout(), CelebrityDto.getLanguages_known(),
                CelebrityDto.getGender(), CelebrityDto.getDob(), CelebrityDto.getDebut(), CelebrityDto.getTags());
        int index = CelebrityDto.getPlans().size();

        for (int i = 0; i < index; i++) {
            LinkedHashMap linkedHashMap = (LinkedHashMap) CelebrityDto.getPlans().get(i);
            ArrayList<Object> Values = new ArrayList<Object>(linkedHashMap.values());
            celebrity.getPrices().add(new Price(Values.get(0).toString(), (Integer) Values.get(1)));
        }
        celebrityRepository.save(celebrity);
        return celebrity.getId();
    }


    @Override
    public T update(T obj, Long id) {
        CelebrityDto CelebrityDto = (CelebrityDto) obj;
        if (celebrityDAO.existsById(id)) {
            Celebrity celebrity = (Celebrity) celebrityDAO.getById(id);
            celebrity.setName(CelebrityDto.getName());
            celebrity.setLanguages_known(CelebrityDto.getLanguages_known());
            celebrity.setAbout(CelebrityDto.getAbout());
            celebrity.setDebut(CelebrityDto.getDebut());
            celebrity.setDob(CelebrityDto.getDob());
            celebrity.setGender(CelebrityDto.getGender());
            celebrity.getPrices().clear();

            for (int i = 0; i < CelebrityDto.getPlans().size(); i++) {
                LinkedHashMap linkedHashMap = (LinkedHashMap) CelebrityDto.getPlans().get(i);
                ArrayList<Object> Values = new ArrayList<Object>(linkedHashMap.values());
                celebrity.getPrices().add(new Price(Values.get(0).toString(), (Integer) Values.get(1)));
            }

            celebrityRepository.save(celebrity);
            return get(id);

        } else {
            return null;
        }

    }

    @Override
    public T delete(Long id) {
        Celebrity celebrity = (Celebrity) celebrityDAO.getById(id);

        if (celebrity != null) {
            List<Object> list = new ArrayList<>();
            Map<Integer, HashMap<String, Object>> map = new HashMap<>();
            for (int i = 0; i < celebrity.getPrices().size(); i++) {
                map.put(i, new HashMap<>());
                map.get(i).put("price", celebrity.getPrices().get(i).getPrice());
                map.get(i).put("type", celebrity.getPrices().get(i).getType());
            }
            list.addAll(map.values());
            log.info("plan: " + list);
            CelebrityDto CelebrityDto = new CelebrityDto(id, celebrity.getName(), celebrity.getAbout(), celebrity.getLanguages_known(),
                    celebrity.getGender(), celebrity.getDob(), celebrity.getDebut(), celebrity.getTags(), list);

            celebrityRepository.deleteById(id);

            return (T) CelebrityDto;
        } else {
            return null;
        }
    }

    @Override
    public T get(Long id) {
        Celebrity celebrity = (Celebrity) celebrityDAO.getById(id);

        if (celebrity != null) {
            List<Object> list = new ArrayList<>();
            Map<Integer, HashMap<String, Object>> map = new HashMap<>();
            for (int i = 0; i < celebrity.getPrices().size(); i++) {
                map.put(i, new HashMap<>());
                map.get(i).put("price", celebrity.getPrices().get(i).getPrice());
                map.get(i).put("type", celebrity.getPrices().get(i).getType());
            }
            list.addAll(map.values());
            log.info("plan: " + list);

            CelebrityDto CelebrityDto = new CelebrityDto(id, celebrity.getName(), celebrity.getAbout(), celebrity.getLanguages_known(),
                    celebrity.getGender(), celebrity.getDob(), celebrity.getDebut(), celebrity.getTags(), list);
            return (T) CelebrityDto;
        } else {
            return null;
        }
    }

    @Override
    public List<T> getAll() {
        int size=celebrityRepository.findAll().size();
        List<?> ids =celebrityRepository.getAllList();
        int count = 0;
        List<T> list = new ArrayList<>();
        while (count < size) {
            if (size > 0)
                list.add(get(((BigInteger) ids.get(count)).longValue()));
            count++;

        }
        return list;
    }
}
