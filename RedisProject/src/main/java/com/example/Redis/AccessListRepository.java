package com.example.Redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.example.security.AccessList;


@Repository
public interface AccessListRepository extends CrudRepository<AccessList,String> {

}
