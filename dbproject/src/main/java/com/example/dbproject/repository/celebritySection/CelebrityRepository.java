package com.example.dbproject.repository.celebritySection;

import com.example.dbproject.model.celebritySection.Celebrity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CelebrityRepository extends JpaRepository<Celebrity,Long> {


}
