//package com.example.dbproject.service;
//
//import com.example.dbproject.model.Provider;
//import com.example.dbproject.repository.CelebrityRepository;
//import com.example.dbproject.repository.CredentialRepository;
//import org.springframework.beans.factory.annotation.Autowired;
//
//public class CredentialService {
//@Autowired
//CredentialRepository celebrityRepository;
//    public void processOAuthPostLogin(String username) {
//        User existUser = celebrityRepository.getUserByUsername(username);
//
//        if (existUser == null) {
//            User newUser = new User();
//            newUser.setUsername(username);
//            newUser.setProvider(Provider.GOOGLE);
//            newUser.setEnabled(true);
//
//            repo.save(newUser);
//        }
//}
