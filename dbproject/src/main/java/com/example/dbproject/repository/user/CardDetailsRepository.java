package com.example.dbproject.repository.user;

import com.example.dbproject.model.user.CardDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

@Repository
public interface CardDetailsRepository extends JpaRepository<CardDetails,Long> {

}
