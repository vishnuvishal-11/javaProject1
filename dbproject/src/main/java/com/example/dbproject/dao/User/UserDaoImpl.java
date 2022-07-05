//package com.example.dbproject.dao.User;
//
//
//import com.example.dbproject.model.user.TringMembership;
//import com.example.dbproject.model.user.Users;
//import com.example.dbproject.repository.userSection.CardDetailsRepository;
//import com.example.dbproject.repository.userSection.TringMembershipRepo;
//import com.example.dbproject.repository.userSection.UserRepository;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//@Component
//@Slf4j
//public class UserDaoImpl implements UserDAO{
//
//    @Autowired
//    UserRepository userRepository;
//    @Autowired
//    CardDetailsRepository card_details_repository;
//    @Autowired
//    TringMembershipRepo tringmembershiprepo;
//    @Override
//    public Long save(Object obj) {
//        log.info(""+obj);
//        userRepository.save(((Users) obj));
//        return ((Users) obj).getId();
//    }
//
//    @Override
//    public Object update(Object obj) {
//        return getById(save(obj));
//    }
//
//    @Override
//    public void delete(Long id) {
//        userRepository.deleteById(id);
//    }
//
//    @Override
//    public Object getById(Long id) {
//        return userRepository.findById(id).orElse(null);
//    }
//
//    @Override
//    public List getAll() {
//        return userRepository.findAll();
//    }
//
//    @Override
//    public Boolean existsById(Long id) {
//        return userRepository.existsById(id);
//    }
//}
