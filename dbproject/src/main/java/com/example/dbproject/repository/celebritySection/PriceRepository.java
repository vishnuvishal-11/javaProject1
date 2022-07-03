package com.example.dbproject.repository.celebritySection;

import com.example.dbproject.model.celebritySection.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PriceRepository extends JpaRepository<Price,Long> {



}
