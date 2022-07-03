package com.example.dbproject.repository.userSection;

import com.example.dbproject.model.userSection.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users,Long> {
}
