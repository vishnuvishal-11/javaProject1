package com.example.dbproject.dao.celebritySection;

import com.example.dbproject.model.celebritySection.Celebrity;
import com.example.dbproject.repository.celebritySection.CelebrityRepository;
import com.example.dbproject.repository.celebritySection.PriceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CelebrityDaoImpl implements CelebrityDAO {
    @Autowired
    CelebrityRepository celebrityRepository;
    @Autowired
    PriceRepository priceRepository;

    @Override
    public Long save(Object obj) {
        celebrityRepository.save((Celebrity) obj);
        return ((Celebrity) obj).getId();
    }

    @Override
    public Object update(Object obj) {

        return getById(save(obj));
    }


    @Override
    public void delete(Long id) {
        celebrityRepository.deleteById(id);
    }

    @Override
    public Object getById(Long id) {
        return celebrityRepository.findById(id).orElse(null);
    }

    @Override
    public List getAll() {
        return celebrityRepository.findAll();
    }

    @Override
    public Boolean existsById(Long id) {
        return celebrityRepository.existsById(id);
    }
}
