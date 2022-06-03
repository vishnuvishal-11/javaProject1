package com.example.RedisProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import security.FilterSelector;

@SpringBootApplication(scanBasePackages ={"controller","Service.QueueInterface","ServiceImpl","Redis","model","ipServices","security","CustomEndpoint"} )
@EnableRedisRepositories
public class RedisProjectApplication {

public static ConfigurableApplicationContext context;
	public static void main(String[] args) {

		context =SpringApplication.run(RedisProjectApplication.class, args);
		FilterSelector filterSelector=new FilterSelector();







	}


}
