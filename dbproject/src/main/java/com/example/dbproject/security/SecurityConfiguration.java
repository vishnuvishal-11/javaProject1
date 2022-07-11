package com.example.dbproject.security;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.security.config.Customizer.withDefaults;
@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfiguration  {
    @Autowired
    private JwtRequestFilter jwtRequestFilter;
    @Bean
    public InMemoryUserDetailsManager userDetailsService() {
//        log.info("in configure 1");
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("admin")
                .roles("ADMIN")
                .build();
        UserDetails user2= User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user,user2);
    }
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        log.info("in configure 2");
//        http.csrf().disable().authorizeRequests().antMatchers("/authenticate").permitAll().
//                anyRequest().authenticated().and().
//                exceptionHandling().and().sessionManagement()
//                .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        http.csrf().disable().cors().and().authorizeHttpRequests((authz) -> authz
                .antMatchers("/user*/*").hasRole("USER")
        ).exceptionHandling().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .httpBasic(withDefaults());
        http.csrf().disable().cors().and()
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest().hasRole("ADMIN")
                ).exceptionHandling().and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .httpBasic(withDefaults());
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}


