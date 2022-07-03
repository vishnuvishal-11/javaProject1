package com.example.dbproject.repository.userSection;

import com.example.dbproject.model.userSection.Card_details;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface Card_details_Repository extends JpaRepository<Card_details,Long> {
}
