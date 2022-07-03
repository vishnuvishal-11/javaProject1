package com.example.dbproject.dao.UserSection;

import com.example.dbproject.model.userSection.Users;
import com.example.dbproject.repository.userSection.Card_details_Repository;
import com.example.dbproject.repository.userSection.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
@Slf4j
public class UserDaoImpl implements UserDAO{

    @Autowired
    UserRepository userRepository;
    @Autowired
    Card_details_Repository card_details_repository;
    @Override
    public Long save(Object obj) {
        log.info(""+obj);
        userRepository.save((Users) obj);
        return ((Users) obj).getId();
    }

    @Override
    public Object update(Object obj) {
        return getById(save(obj));
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Object getById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List getAll() {
        return userRepository.findAll();
    }

    @Override
    public Boolean existsById(Long id) {
        return userRepository.existsById(id);
    }
}
