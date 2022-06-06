package security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.stereotype.Component;

import javax.servlet.Filter;

@Component
@Configuration
@Order(1)
public class securityFilterConfig extends WebSecurityConfigurerAdapter  {

    Logger logger = LoggerFactory.getLogger(securityFilterConfig.class);
    @Autowired
    AccessFilter accessFilter;
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        logger.info("In security filter ...");

        http.csrf().disable()
                .authorizeRequests()
            //    .antMatchers("/info").hasIpAddress("127.0.0.0").anyRequest().denyAll()
                 .antMatchers("/display").permitAll()
                .antMatchers("/health").permitAll()
                .antMatchers("/size").permitAll()
                .antMatchers("/delete").permitAll()
        ;


    }

}






