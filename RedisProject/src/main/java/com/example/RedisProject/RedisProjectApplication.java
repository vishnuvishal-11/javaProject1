package com.example.RedisProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
//import security.AccessFilter;
//import security.AccessFilter;
//import security.RedisFilter;
//import Redis.FilterSelector;

@SpringBootApplication(scanBasePackages ={"controller","Service.QueueInterface","ServiceImpl","Redis","model","ipServices","security","CustomEndpoint","security.Factory"} )
@EnableRedisRepositories
public class RedisProjectApplication {
public static ConfigurableApplicationContext context;
	public static void main(String[] args) {

		context =SpringApplication.run(RedisProjectApplication.class, args);


	}



}
