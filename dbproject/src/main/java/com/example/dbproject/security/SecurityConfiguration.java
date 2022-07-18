package com.example.dbproject.security;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.authserver.OAuth2AuthorizationServerConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static org.springframework.security.config.Customizer.withDefaults;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebMvcAutoConfiguration {
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
        UserDetails user2 = User.withDefaultPasswordEncoder()
                .username("user")
                .password("user")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user, user2);
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().cors().and().authorizeHttpRequests((authz) -> authz .antMatchers(HttpMethod.POST,"/user/*")
                        .hasAnyRole("USER", "CELEBRITY","ADMIN", "SUPER_ADMIN")
                       .antMatchers(HttpMethod.GET,"/user/*")
                        .hasAnyRole("USER", "CELEBRITY","ADMIN", "SUPER_ADMIN")
                        .antMatchers(HttpMethod.DELETE,"/user/*")
                        .hasAnyRole("USER", "CELEBRITY","ADMIN", "SUPER_ADMIN")
                        .antMatchers(HttpMethod.PUT,"/user/*")
                        .hasAnyRole("USER", "CELEBRITY","ADMIN", "SUPER_ADMIN")
                        .regexMatchers( "/authenticate", "/signup")
                        .hasAnyRole("USER", "CELEBRITY","ADMIN", "SUPER_ADMIN")
                        .regexMatchers( "/auth", "/enter")
                        .permitAll()
                ).exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .oauth2Login().defaultSuccessUrl("http://localhost:8082/enter",true).and()
              //  .formLogin().loginPage("/auth.html").successForwardUrl("http://localhost:8082/swagger-ui/index.html").and()
                .httpBasic(withDefaults());

        http.csrf().disable().cors().and()
                .authorizeHttpRequests((authz) -> authz
                        .anyRequest().hasAnyRole("ADMIN", "SUPER_ADMIN")
                ).exceptionHandling().and().sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().oauth2Login()
                .defaultSuccessUrl("http://localhost:8082/enter",true).and()
              //  .formLogin().loginPage("/auth.html").successForwardUrl("http://localhost:8082/swagger-ui/index.html").and()
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


