package com.example.firstSpringProj;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication(scanBasePackages ={"controller","Service","ServiceImpl","model"} )
public class FirstSpringProjApplication {
//    static List<String> list;
//    private Implementation imple;
//    static ConfigurableApplicationContext context;



    public static void main(String[] args) {
        SpringApplication.run(FirstSpringProjApplication.class, args);
//        Implementation obj1 = context.getBean(Implementation.class);
//        UserAddress obj = context.getBean(UserAddress.class);
//        UserAddress obj2 = context.getBean(UserAddress.class);
//        UserAddress obj3 = context.getBean(UserAddress.class);
//        obj.set("emp1", "25", "11/11/1995", "chennai");
//        obj2.set("emp2", "26", "13/01/1995", "chennai");
//        obj3.set("emp3", "27", "23/07/1995", "chennai");
//        obj1.enque(obj);
//        obj1.enque(obj2);
//        //context.getBean(FirstSpringProjApplication.class).post(obj1,obj3);
//        list = obj1.display();
    }




}

