package com.example.dbproject.repository;

import com.example.dbproject.model.TringMembership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TringMembershipRepo extends JpaRepository<TringMembership,Long> {
//    @Query(value = "select user_id from tring_membership  where user_id =:id",nativeQuery = true)
//    Long findMemId(@Param("id") Long id);

//    @Query(value = "delete from tring_membership twhere t.user_fk2=id ",nativeQuery = true)
//    Boolean setFkId(Long id);

}
