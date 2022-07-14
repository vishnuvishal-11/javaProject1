package com.example.dbproject;

import liquibase.integration.spring.SpringLiquibase;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.sql.DataSource;

import static org.springframework.orm.hibernate5.SessionFactoryUtils.getDataSource;

@SpringBootApplication(scanBasePackages = {"com.example.dbproject.*", "com.example.dbproject.controller", "com.example.dbproject.repository", "com.example.dbproject.model", "com.example.dbproject.service", "com.example.dbproject.CelebrityDto","com.example.dbproject.swagger"})
@EnableJpaRepositories(basePackages = "com.example.dbproject.repository")
@EntityScan(basePackages = "com.example.dbproject.model")
@EnableAutoConfiguration//(exclude = LiquibaseAutoConfiguration.class)
@Slf4j
public class DbprojectApplication {
    @Autowired
    private Environment env;

    public static void main(String[] args) {
        SpringApplication.run(DbprojectApplication.class, args);
    }

    @Bean
    public SpringLiquibase liquibase() {
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setChangeLog("classpath:/db/changeLog/db.changelog-master.xml");
        liquibase.setDataSource(dataSource());
        return liquibase;
    }
    @Bean
    public LiquibaseProperties liquibaseProperties() {
        return new LiquibaseProperties();
    }
    @Bean
    public DataSource dataSource() {
        final DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("spring.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("spring.datasource.url"));
        dataSource.setUsername(env.getProperty("spring.datasource.username"));
        dataSource.setPassword(env.getProperty("spring.datasource.password"));
        dataSource.setSchema("public");
        return dataSource;
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