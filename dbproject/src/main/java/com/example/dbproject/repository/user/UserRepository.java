package com.example.dbproject.repository.user;

import com.example.dbproject.model.user.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {

    @Query(value = "select users.user_id from users",nativeQuery = true)
    List<?> getAllList();
    @Query(value = "select users.email from users",nativeQuery = true)
    List<?> getAllEmail();
    @Query(value = "select users.invite_code from users ",nativeQuery = true)
    List<?> getAllInviteCode();
}
