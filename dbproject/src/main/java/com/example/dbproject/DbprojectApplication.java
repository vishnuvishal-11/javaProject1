package com.example.dbproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages ={"com.example.dbproject.*", "com.example.dbproject.controller","com.example.dbproject.repository" ,"com.example.dbproject.model","com.example.dbproject.service","com.example.dbproject.CelebrityDto"})
@EnableJpaRepositories(basePackages="com.example.dbproject.repository")
@EnableAutoConfiguration
@EntityScan(basePackages="com.example.dbproject.model")
public class DbprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbprojectApplication.class, args);
	}

}
