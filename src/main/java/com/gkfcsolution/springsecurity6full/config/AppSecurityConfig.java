package com.gkfcsolution.springsecurity6full.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.asm.TypeReference;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 2025 at 17:08
 * File: null.java
 * Project: spring-security6-full
 *
 * @author Frank GUEKENG
 * @date 30/09/2025
 * @time 17:08
 */
@Configuration
@EnableWebSecurity
public class AppSecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf-> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/employees/fetchAll").hasRole("ADMIN")
                        .requestMatchers("/api/v1/employees/fetch/**").hasAnyRole("USER", "ADMIN")
                        .requestMatchers("/api/v1/employees/**").permitAll()
                        .anyRequest().authenticated()

                )
                .httpBasic(Customizer.withDefaults());

        return http.build();

     /*   http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(getSecureServicesList()).authenticated()
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();*/

    }

    private String[] getSecureServicesList(){
        InputStream fileStream = TypeReference.class.getResourceAsStream("/static/secureservices.json");

        ObjectMapper objectMapper = new ObjectMapper();
        List<String> urlList = new ArrayList<>();
        try {
            urlList = objectMapper.readValue(fileStream, ArrayList.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        String[] urls =  urlList.stream().toArray(String[]::new);
        return urls;
    }
}
