package com.example.dbproject.repository;

import com.example.dbproject.model.Credentials;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CredentialRepository extends JpaRepository<Credentials,Long> {




    List<Credentials> findByUserName(String username);
}
