package com.example.RedisProject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

@SpringBootApplication(scanBasePackages = {"controller", "Service.QueueInterface", "ServiceImpl", "rabbitconfig", "Redis", "model", "ipServices", "security", "CustomEndpoint", "security.Factory"})
@EnableRedisRepositories
public class RedisProjectApplication {
    public static void main(String[] args) {

       SpringApplication.run(RedisProjectApplication.class, args);

    }




}
