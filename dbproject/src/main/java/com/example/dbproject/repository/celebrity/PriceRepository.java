package com.example.dbproject.repository.celebrity;

import com.example.dbproject.model.celebrity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price,Long> {



}
