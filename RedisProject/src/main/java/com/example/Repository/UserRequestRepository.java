package com.example.Repository;

import com.example.model.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRequestRepository extends CrudRepository<UserRequest,Long> {

//void saveObj(UserRequest userRequest);

}
