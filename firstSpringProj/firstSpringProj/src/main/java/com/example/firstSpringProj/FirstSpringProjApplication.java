package com.example.firstSpringProj;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;



@SpringBootApplication(scanBasePackages ={"controller","Service.QueueInterface","ServiceImpl","model","ipServices","security","CustomEndpoint"} )
public class FirstSpringProjApplication {
 public  static ConfigurableApplicationContext context;
    public static void main(String[] args) {
        context= SpringApplication.run(FirstSpringProjApplication.class, args);

    }
}

