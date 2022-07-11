package com.example.dbproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.example.dbproject.*", "com.example.dbproject.controller", "com.example.dbproject.repository", "com.example.dbproject.model", "com.example.dbproject.service", "com.example.dbproject.CelebrityDto","com.example.dbproject.swagger"})
@EnableJpaRepositories(basePackages = "com.example.dbproject.repository")
@EnableAutoConfiguration
@EntityScan(basePackages = "com.example.dbproject.model")
//@EnableSwagger2
//@EnableWebMvc
public class DbprojectApplication {

    public static void main(String[] args) {
        SpringApplication.run(DbprojectApplication.class, args);
    }



}


//sample inputs
/*
* {
    "name": "user1",
    "about": "about vivek",
    "languages_known": [
        "english",
        "hindi",
        "tamil"
    ],
    "gender": "male",
    "dob": "2019-04-28T14:45:15",
    "debut": "vs Pakistan at karachi",
    "tags": [
        "#Actor",
        "#fitness",
        "#BigBoss"
    ],
    "plans": [
        {
            "type": "insta_dm",
            "price": 20000
        },
        {
            "type": "recorded_video",
            "price": 10000
        }
    ],
    "qualification":"Engineering in cs",
    "birth_place": "chennai",
    "fb_id":"user fb_id",
    "insta_id":"user insta_id",
    "marital_status":"single"


}
*
* sample postman query searches..../.....
*
* 1=> http://localhost:8082/celebrity/?search=qualification:lawyer,'marital_status:single,marital_status:married
* 2=> http://localhost:8082/celebrity/?search=qualification!lawyer,marital_status!single
*3=> http://localhost:8082/celebrity/?search=marital_status:single&offset=0&pagesize=6&field=id
*
*  negation is !  used in 2
* for or use '  and for and use comma without anything
* use : for equal
* can use () is necessary
*  */