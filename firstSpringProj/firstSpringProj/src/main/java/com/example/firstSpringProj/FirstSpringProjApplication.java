package com.example.firstSpringProj;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication(scanBasePackages ={"controller","Service","ServiceImpl","model"} )
public class FirstSpringProjApplication {

    public static void main(String[] args) {
                    SpringApplication.run(FirstSpringProjApplication.class, args);

    }
}

