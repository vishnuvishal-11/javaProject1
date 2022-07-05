package com.example.dbproject.repository.user;

import com.example.dbproject.model.user.TringMembership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TringMembershipRepo extends JpaRepository<TringMembership,Long> {
//    @Query(value = "select membership_code from tring_membership  where user_id =:id",nativeQuery = true)
//    Long findMemId(@Param("id") Long id);

//    @Query(value = "delete from tring_membership twhere t.user_fk2=id ",nativeQuery = true)
//    Boolean setFkId(Long id);

}
