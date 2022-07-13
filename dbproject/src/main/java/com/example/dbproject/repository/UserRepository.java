package com.example.dbproject.repository;

import com.example.dbproject.model.Celebrity;
import com.example.dbproject.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Users,Long>, JpaSpecificationExecutor<Users> {

    @Query(value = "select CAST(u.user_id as varchar), u.email, u.invite_code, u.user_name, u.password, u.referred_code, u.status from users u",nativeQuery = true)
    List<Users> getAllList();
    @Query(value = "select users.email from users",nativeQuery = true)
    List<?> getAllEmail();
    @Query(value = "select users.invite_code from users ",nativeQuery = true)
    List<?> getAllInviteCode();
}
