package com.example.dbproject.repository.celebrity;

import com.example.dbproject.model.celebrity.Celebrity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CelebrityRepository extends JpaRepository<Celebrity,Long> {
    @Query(value = "select celebrity_id from celebrity",nativeQuery = true)
    List<?> getAllList();

}
