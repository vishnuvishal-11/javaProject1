package com.example.RedisProject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication(scanBasePackages ={"controller","Service.QueueInterface","ServiceImpl","rabbitconfig","Redis","model","ipServices","security","CustomEndpoint","security.Factory"} )
@EnableRedisRepositories
public class RedisProjectApplication {
public static ConfigurableApplicationContext context;
	public static void main(String[] args) {

		context =SpringApplication.run(RedisProjectApplication.class, args);


	}




}
